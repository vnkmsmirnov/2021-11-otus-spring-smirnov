package ru.otus.library.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.library.model.AuthorEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Repository
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public AuthorEntity findById(Long id) {
        var params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbc.queryForObject("SELECT * FROM AUTHORS " +
                                       "WHERE ID = :id", params, new AuthorRowMapper());
    }

    @Override
    public List<AuthorEntity> findAll() {
        return jdbc.query("SELECT * FROM AUTHORS", new AuthorRowMapper());
    }

    @Override
    public Long save(AuthorEntity author) {
        var params = new MapSqlParameterSource();
        params.addValue("id", author.getId());
        params.addValue("firstName", author.getFirstName());
        params.addValue("lastName", author.getLastName());
        var keyHolder = new GeneratedKeyHolder();
        jdbc.update("MERGE INTO AUTHORS USING DUAL " +
                        "ON (ID IS NOT NULL AND ID = :id) " +
                        "WHEN MATCHED THEN " +
                            "UPDATE SET " +
                                "FIRST_NAME = :firstName, " +
                                "LAST_NAME = :lastName " +
                        "WHEN NOT MATCHED THEN " +
                            "INSERT (FIRST_NAME, LAST_NAME) " +
                            "VALUES (:firstName, :lastName)", params, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    private static class AuthorRowMapper implements RowMapper<AuthorEntity> {

        @Override
        public AuthorEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            return AuthorEntity.builder()
                    .id(rs.getLong("ID"))
                    .firstName(rs.getString("FIRST_NAME"))
                    .lastName(rs.getString("LAST_NAME"))
                    .build();
        }
    }
}
