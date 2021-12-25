package ru.otus.library.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.library.model.AuthorEntity;
import ru.otus.library.model.BookEntity;
import ru.otus.library.model.GenreEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Repository
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations jdbc;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Override
    public BookEntity findById(Long id) {
        var params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbc.queryForObject("SELECT B.ID AS ID, " +
                                              "B.TITLE AS TITLE, " +
                                              "B.PAGES AS PAGES, " +
                                              "G.ID AS GENRE_ID, " +
                                              "G.NAME AS GENRE_NAME, " +
                                              "A.ID AS AUTHOR_ID, " +
                                              "A.FIRST_NAME AS AUTHOR_FIRST_NAME, " +
                                              "A.LAST_NAME AS AUTHOR_LAST_NAME " +
                                       "FROM BOOKS B " +
                                           "LEFT JOIN GENRES G " +
                                                "ON B.GENRE_ID = G.ID " +
                                           "LEFT JOIN AUTHORS A " +
                                                "ON B.AUTHOR_ID = A.ID " +
                                       "WHERE B.ID = :id", params, new BookRowMapper());
    }

    @Override
    public List<BookEntity> findAll() {
        return jdbc.query("SELECT B.ID AS ID, " +
                                     "B.TITLE AS TITLE, " +
                                     "B.PAGES AS PAGES, " +
                                     "G.ID AS GENRE_ID, " +
                                     "G.NAME AS GENRE_NAME, " +
                                     "A.ID AS AUTHOR_ID, " +
                                     "A.FIRST_NAME AS AUTHOR_FIRST_NAME, " +
                                     "A.LAST_NAME AS AUTHOR_LAST_NAME " +
                             "FROM BOOKS B " +
                                "LEFT JOIN GENRES G " +
                                    "ON B.GENRE_ID = G.ID " +
                                "LEFT JOIN AUTHORS A " +
                                    "ON B.AUTHOR_ID = A.ID", new BookRowMapper());
    }

    @Override
    public Long save(BookEntity book) {
        var genreId = genreDao.save(book.getGenre());
        var authorId = authorDao.save(book.getAuthor());
        var params = new MapSqlParameterSource();
        params.addValue("id", book.getId());
        params.addValue("title", book.getTitle());
        params.addValue("pages", book.getPages());
        params.addValue("genreId", genreId);
        params.addValue("authorId", authorId);
        var keyHolder = new GeneratedKeyHolder();
        jdbc.update("MERGE INTO BOOKS USING DUAL " +
                        "ON (TITLE IS NOT NULL AND TITLE = :title) " +
                        "WHEN MATCHED THEN " +
                            "UPDATE SET " +
                                "PAGES = :pages, " +
                                "AUTHOR_ID = :authorId, " +
                                "GENRE_ID = :genreId " +
                        "WHEN NOT MATCHED THEN " +
                            "INSERT (TITLE, PAGES, AUTHOR_ID, GENRE_ID) " +
                            "VALUES (:title, :pages, :authorId, :genreId)", params, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public void deleteById(Long id) {
        var params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbc.update("DELETE FROM BOOKS WHERE ID = :id", params);
    }

    private static class BookRowMapper implements RowMapper<BookEntity> {
        @Override
        public BookEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            return BookEntity.builder()
                    .id(rs.getLong("ID"))
                    .title(rs.getString("TITLE"))
                    .pages(rs.getInt("PAGES"))
                    .genre(GenreEntity.builder()
                            .id(rs.getLong("GENRE_ID"))
                            .name(rs.getString("GENRE_NAME"))
                            .build())
                    .author(AuthorEntity.builder()
                            .id(rs.getLong("AUTHOR_ID"))
                            .firstName(rs.getString("AUTHOR_FIRST_NAME"))
                            .lastName(rs.getString("AUTHOR_LAST_NAME"))
                            .build())
                    .build();
        }
    }
}
