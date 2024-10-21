package com.example.demo.dao.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserDaoImpl implements UserDao {
    private final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;
    private GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

    @Override
    public Optional<User> findUserById(int id) {
        SqlRowSet userRows = jdbcTemplate.queryForRowSet("select * from users where id = ?", id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (userRows.next()) {
            log.info("Найден пользователь: {} {}", userRows.getString("id"),
                    userRows.getString("name"));

            User user = User.builder()
                    .id(userRows.getInt("id"))
                    .name(userRows.getString("name"))
                    .login(userRows.getString("login"))
                    .birthday(LocalDate.parse(Objects.requireNonNull(userRows.getString("birthday")), formatter))
                    .email(userRows.getString("email"))
                    .build();

            return Optional.ofNullable(user);
        } else {
            log.info("Пользователь с идентификатором {} не найден.", id);
            return Optional.empty();
        }
    }

    @Override
    public List<User> getUsers() {
        String sql = "select * from users";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return jdbcTemplate.query(sql, (resultSet, rowNum) ->
                User.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .login(resultSet.getString("login"))
                        .birthday(LocalDate.parse(Objects.requireNonNull(resultSet.getString("birthday")), formatter))
                        .email(resultSet.getString("email"))
                        .build()
        );
    }

    @Override
    public Optional<User> create(User user) {
        String sql = "INSERT INTO users (email, login, name, birthday) VALUES(?, ?, ?, ?) RETURNING id;";
        int rowsAffected = jdbcTemplate.update(conn -> {

            PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[]{"id"});

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setObject(4, user.getBirthday());

            return preparedStatement;

        }, generatedKeyHolder);

        return this.findUserById(Objects.requireNonNull(generatedKeyHolder.getKey()).intValue());
    }

    @Override
    public Optional<User> update(User user) {
        String sql = "UPDATE users SET email = ?, login = ?, name = ?, birthday = ? WHERE id = ?;";
        int rowsAffected = jdbcTemplate.update(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[]{"id"});

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setObject(4, user.getBirthday());
            preparedStatement.setObject(5, user.getId());

            return preparedStatement;

        }, generatedKeyHolder);

        return this.findUserById(Objects.requireNonNull(generatedKeyHolder.getKey()).intValue());
    }
}

