package ru.otus.library.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.library.mongo.model.AuthorDocument;
import ru.otus.library.mongo.repository.AuthorMongoRepository;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class AuthorRepositoryTest {

    private static final AuthorDocument SAVED_AUTHOR = AuthorDocument.builder()
            .id("A_TEST_ID_1")
            .name("TEST_AUTHOR_1")
            .build();

    private static final AuthorDocument EXPECTED_AUTHOR = AuthorDocument.builder()
            .id("A_TEST_ID_2")
            .name("TEST_AUTHOR_2")
            .build();

    @Autowired
    private AuthorMongoRepository authorRepository;

    @Test
    public void findByIdTest() {
        var result = authorRepository.findById(SAVED_AUTHOR.getId());
        assertThat(result).isPresent().get()
                .usingRecursiveComparison()
                .isEqualTo(SAVED_AUTHOR);
    }

    @Test
    void findAllTest() {
        var result = authorRepository.findAll();
        assertThat(result)
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(Collections.singletonList(SAVED_AUTHOR));
    }

    @Test
    void saveTest() {
        var result = authorRepository.save(EXPECTED_AUTHOR);
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(EXPECTED_AUTHOR);
        authorRepository.deleteById(EXPECTED_AUTHOR.getId());
    }

    @Test
    void deleteByIdTest() {
        var savedAuthor = authorRepository.findById(SAVED_AUTHOR.getId());
        assertThat(savedAuthor).isPresent().get()
                .usingRecursiveComparison()
                .isEqualTo(SAVED_AUTHOR);
        authorRepository.deleteById(SAVED_AUTHOR.getId());
        var result = authorRepository.findById(SAVED_AUTHOR.getId());
        assertThat(result)
                .isEqualTo(Optional.empty());
        authorRepository.save(savedAuthor.get());
    }
}
