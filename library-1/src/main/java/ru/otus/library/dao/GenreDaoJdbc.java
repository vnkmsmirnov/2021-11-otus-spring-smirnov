package ru.otus.library.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.library.model.GenreEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Repository
public class GenreDaoJdbc implements GenreDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public GenreEntity findById(Long id) {
        var params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbc.queryForObject("SELECT * FROM GENRES " +
                "WHERE ID = :id", params, new GenreRowMapper());
    }

    @Override
    public List<GenreEntity> findAll() {
        return jdbc.query("SELECT * FROM GENRES", new GenreRowMapper());
    }

    @Override
    public Long save(GenreEntity genre) {
        var params = new MapSqlParameterSource();
        params.addValue("id", genre.getId());
        params.addValue("name", genre.getName());
        var keyHolder = new GeneratedKeyHolder();
        jdbc.update("MERGE INTO GENRES USING DUAL " +
                        "ON (ID IS NOT NULL AND ID = :id) " +
                        "WHEN MATCHED THEN " +
                            "UPDATE SET " +
                                "NAME = :name " +
                        "WHEN NOT MATCHED THEN " +
                            "INSERT (NAME) " +
                            "VALUES (:name)", params, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    private static class GenreRowMapper implements RowMapper<GenreEntity> {

        @Override
        public GenreEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            return GenreEntity.builder()
                    .id(rs.getLong("ID"))
                    .name(rs.getString("NAME"))
                    .build();
        }
    }
}
