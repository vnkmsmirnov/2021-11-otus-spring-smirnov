package ru.otus.library.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.library.model.AuthorDocument;
import ru.otus.library.model.BookDocument;
import ru.otus.library.model.CommentDocument;
import ru.otus.library.model.GenreDocument;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class BookRepositoryTest {

    private static final BookDocument SAVED_BOOK = BookDocument.builder()
            .id("B_TEST_ID_1")
            .title("TEST_TITLE_1")
            .pages(240)
            .author(AuthorDocument.builder()
                    .name("TEST_AUTHOR_1")
                    .build())
            .genre(GenreDocument.builder()
                    .name("TEST_GENRE_1")
                    .build())
            .comments(Collections.singletonList(CommentDocument.builder()
                            .id("C_TEST_ID_1")
                            .text("TEST_COMMENT_1")
                            .bookId("B_TEST_ID_1")
                            .build()))
            .build();

    private static final BookDocument EXPECTED_BOOK = BookDocument.builder()
            .id("B_TEST_ID_1")
            .title("TEST_TITLE_1")
            .pages(240)
            .author(AuthorDocument.builder()
                    .name("TEST_AUTHOR_1")
                    .build())
            .genre(GenreDocument.builder()
                    .name("TEST_GENRE_1")
                    .build())
            .comments(Collections.singletonList(CommentDocument.builder()
                    .id("C_TEST_ID_1")
                    .text("TEST_COMMENT_1")
                    .bookId("B_TEST_ID_1")
                    .build()))
            .build();

    @Autowired
    private BookRepository bookRepository;

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
                .isEqualTo(Collections.singletonList(SAVED_BOOK));
    }

    @Test
    void saveTest() {
        var result = bookRepository.save(EXPECTED_BOOK);
        assertThat(result)
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
        bookRepository.save(savedBook.get());
    }
}