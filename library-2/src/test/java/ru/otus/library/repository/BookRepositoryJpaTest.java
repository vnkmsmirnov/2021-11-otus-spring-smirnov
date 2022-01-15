package ru.otus.library.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.library.model.AuthorEntity;
import ru.otus.library.model.BookEntity;
import ru.otus.library.model.CommentEntity;
import ru.otus.library.model.GenreEntity;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({BookRepositoryJpa.class, GenreRepositoryJpa.class, AuthorRepositoryJpa.class, CommentRepositoryJpa.class})
class BookRepositoryJpaTest {
    private static final BookEntity SAVED_BOOK = BookEntity.builder()
            .id(1L)
            .title("TEST_BOOK_1")
            .pages(999)
            .authorId(1L)
            .genreId(1L)
            .genre(GenreEntity.builder()
                    .id(1L)
                    .name("TEST_GENRE_1")
                    .build())
            .author(AuthorEntity.builder()
                    .id(1L)
                    .firstName("FIRST_NAME_1")
                    .lastName("LAST_NAME_1")
                    .build())
            .comments(Collections.singletonList(CommentEntity.builder()
                    .id(1L)
                    .text("TEST_COMMENT_1")
                    .bookId(1L)
                    .build()))
            .build();

    private static final BookEntity EXPECTED_BOOK = BookEntity.builder()
            .id(2L)
            .title("TEST_BOOK_2")
            .pages(200)
            .authorId(1L)
            .genreId(1L)
            .genre(GenreEntity.builder()
                    .id(2L)
                    .name("TEST_GENRE_2")
                    .build())
            .author(AuthorEntity.builder()
                    .id(2L)
                    .firstName("FIRST_NAME_2")
                    .lastName("LAST_NAME_2")
                    .build())
            .comments(Collections.singletonList(CommentEntity.builder()
                    .id(2L)
                    .text("TEST_COMMENT_2")
                    .bookId(2L)
                    .build()))
            .build();

    @Autowired
    private BookRepositoryJpa bookRepository;

    @Test
    void findByIdTest() {
        var result = bookRepository.findById(SAVED_BOOK.getId());
        assertThat(result).isPresent().get()
                .usingRecursiveComparison()
                .isEqualTo(SAVED_BOOK);
    }

    @Test
    void findAllTest() {
        var result = bookRepository.findAll();
        assertThat(result)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(SAVED_BOOK);
    }

    @Test
    void saveTest() {
        bookRepository.save(EXPECTED_BOOK);
        var result = bookRepository.findById(EXPECTED_BOOK.getId());
        assertThat(result).isPresent().get()
                .usingRecursiveComparison()
                .isEqualTo(EXPECTED_BOOK);
    }

    @Test
    void deleteByIdTest() {
        var savedBook = bookRepository.findById(SAVED_BOOK.getId());
        assertThat(savedBook).isPresent().get()
                .usingRecursiveComparison()
                .isEqualTo(SAVED_BOOK);
        bookRepository.deleteById(SAVED_BOOK.getId());
        var result = bookRepository.findById(SAVED_BOOK.getId());
        assertThat(result)
                .isEqualTo(Optional.empty());
    }
}