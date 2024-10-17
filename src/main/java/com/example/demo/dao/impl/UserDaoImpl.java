package com.example.demo.dao.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserDaoImpl implements UserDao {
    private final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<User> findUserById(int id) {
        SqlRowSet userRows = jdbcTemplate.queryForRowSet("select * from users where id = ?", id);
        if (userRows.next()) {
            log.info("Найден пользователь: {} {}", userRows.getString("id"),
                    userRows.getString("name"));

            User user = User.builder()
                    .id(userRows.getInt("id"))
                    .name(userRows.getString("name"))
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

        return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            return User.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .build();
        });
    }

//    @Override
//    public Optional<User> create(User user) {
//        String sql =  "select * from users where";
//        return Optional.empty();
//    }

//    private User makeUser()
}

