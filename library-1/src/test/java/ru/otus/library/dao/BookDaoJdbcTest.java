package ru.otus.library.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.library.model.AuthorEntity;
import ru.otus.library.model.BookEntity;
import ru.otus.library.model.GenreEntity;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
@Import({BookDaoJdbc.class, GenreDaoJdbc.class, AuthorDaoJdbc.class})
class BookDaoJdbcTest {
    private static final BookEntity SAVED_BOOK = BookEntity.builder()
            .id(1L)
            .title("TEST_BOOK_1")
            .pages(999)
            .genre(GenreEntity.builder()
                    .id(1L)
                    .name("TEST_GENRE_1")
                    .build())
            .author(AuthorEntity.builder()
                    .id(1L)
                    .firstName("FIRST_NAME_1")
                    .lastName("LAST_NAME_1")
                    .build())
            .build();

    private static final BookEntity EXPECTED_BOOK = BookEntity.builder()
            .id(2L)
            .title("TEST_BOOK_2")
            .pages(200)
            .genre(GenreEntity.builder()
                    .id(2L)
                    .name("TEST_GENRE_2")
                    .build())
            .author(AuthorEntity.builder()
                    .id(2L)
                    .firstName("FIRST_NAME_2")
                    .lastName("LAST_NAME_2")
                    .build())
            .build();

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @Test
    void findByIdTest() {
        var result = bookDaoJdbc.findById(SAVED_BOOK.getId());
        assertThat(result).usingRecursiveComparison().isEqualTo(SAVED_BOOK);
    }

    @Test
    void findAll() {
        var result = bookDaoJdbc.findAll();
        assertThat(result)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(SAVED_BOOK);
    }

    @Test
    void saveTest() {
        bookDaoJdbc.save(EXPECTED_BOOK);
        var result = bookDaoJdbc.findById(EXPECTED_BOOK.getId());
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(EXPECTED_BOOK);
    }

    @Test
    void deleteByIdTest() {
        assertThatCode(() -> bookDaoJdbc.findById(SAVED_BOOK.getId()))
                .doesNotThrowAnyException();
        bookDaoJdbc.deleteById(SAVED_BOOK.getId());
        assertThatThrownBy(() -> bookDaoJdbc.findById(SAVED_BOOK.getId()))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}