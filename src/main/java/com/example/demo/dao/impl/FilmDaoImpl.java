package com.example.demo.dao.impl;

import com.example.demo.dao.FilmDao;
import com.example.demo.model.Film;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class FilmDaoImpl implements FilmDao {
    private final Logger log = LoggerFactory.getLogger(FilmDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Film> findFilmById(int id) {
        SqlRowSet userRows = jdbcTemplate.queryForRowSet("select * from films where id = ?", id);
        if (userRows.next()) {
            log.info("Найден фильм: {} {}", userRows.getString("id"),
                    userRows.getString("name"));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            Film film = Film.builder()
                    .id(userRows.getInt("id"))
                    .name(userRows.getString("name"))
                    .duration(userRows.getInt("duration"))
                    .description(userRows.getString("description"))
                    .releaseDate(LocalDate.parse(Objects.requireNonNull(userRows.getString("releasedate")), formatter))
                    .build();

            return Optional.ofNullable(film);
        } else {
            log.info("Фильм с идентификатором {} не найден.", id);
            return Optional.empty();
        }
    }
}
