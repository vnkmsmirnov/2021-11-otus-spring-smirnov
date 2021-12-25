package ru.otus.library.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.library.dao.BookDao;
import ru.otus.library.dto.Author;
import ru.otus.library.dto.Book;
import ru.otus.library.dto.Genre;
import ru.otus.library.mapping.BookMapper;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class BookServiceImplTest {
    private static final Book SAVED_BOOK = Book.builder()
            .id(1L)
            .title("TEST_BOOK_1")
            .pages(999)
            .genre(Genre.builder()
                    .id(1L)
                    .name("TEST_GENRE_1")
                    .build())
            .author(Author.builder()
                    .id(1L)
                    .firstName("FIRST_NAME_1")
                    .lastName("LAST_NAME_1")
                    .build())
            .build();

    private static final Book EXPECTED_BOOK = Book.builder()
            .id(2L)
            .title("TEST_BOOK_2")
            .pages(200)
            .genre(Genre.builder()
                    .id(2L)
                    .name("TEST_GENRE_2")
                    .build())
            .author(Author.builder()
                    .id(2L)
                    .firstName("FIRST_NAME_2")
                    .lastName("LAST_NAME_2")
                    .build())
            .build();

    @Autowired
    private BookServiceImpl bookService;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private BookMapper bookMapper;

    @Test
    void getTest() {
        var result = bookService.get(1L);
        assertThat(result).usingRecursiveComparison().isEqualTo(SAVED_BOOK);
    }

    @Test
    void getAllTest() {
        var result = bookService.getAll();
        assertThat(result)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(SAVED_BOOK, EXPECTED_BOOK);
    }

    @Test
    void saveTest() {
        bookService.save(EXPECTED_BOOK);
        var result = bookService.get(EXPECTED_BOOK.getId());
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(EXPECTED_BOOK);
    }

    @Test
    void deleteTest() {
        assertThatCode(() -> bookService.get(SAVED_BOOK.getId()))
                .doesNotThrowAnyException();
        bookService.delete(SAVED_BOOK.getId());
        assertThatThrownBy(() -> bookService.get(SAVED_BOOK.getId()))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}