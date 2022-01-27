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

    private static final CommentDocument SAVED_COMMENT = CommentDocument.builder()
            .id("C_TEST_ID_1")
            .text("TEST_COMMENT_1")
            .build();

    private static final CommentDocument EXPECTED_COMMENT = CommentDocument.builder()
            .id("C_TEST_ID_2")
            .text("TEST_COMMENT_2")
            .build();

    private static final BookDocument SAVED_BOOK = BookDocument.builder()
            .id("B_TEST_ID_1")
            .title("TEST_TITLE_1")
            .pages(240)
            .author(AuthorDocument.builder()
                    .id("A_TEST_ID_1")
                    .name("TEST_AUTHOR_1")
                    .build())
            .genre(GenreDocument.builder()
                    .id("G_TEST_ID_1")
                    .name("TEST_GENRE_1")
                    .build())
            .comments(Collections.singletonList(SAVED_COMMENT))
            .build();

    private static final AuthorDocument EXPECTED_AUTHOR = AuthorDocument.builder()
            .id("A_TEST_ID_2")
            .name("TEST_AUTHOR_2")
            .build();

    private static final GenreDocument EXPECTED_GENRE = GenreDocument.builder()
            .id("G_TEST_ID_2")
            .name("TEST_GENRE_2")
            .build();

    private static final BookDocument EXPECTED_BOOK = BookDocument.builder()
            .id("B_TEST_ID_2")
            .title("TEST_TITLE_2")
            .pages(240)
            .comments(Collections.singletonList(EXPECTED_COMMENT))
            .build();

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;

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
        EXPECTED_BOOK.setAuthor(authorRepository.save(EXPECTED_AUTHOR));
        EXPECTED_BOOK.setGenre(genreRepository.save(EXPECTED_GENRE));
        var result = bookRepository.save(EXPECTED_BOOK);
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(EXPECTED_BOOK);
        bookRepository.deleteById(EXPECTED_BOOK.getId());
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