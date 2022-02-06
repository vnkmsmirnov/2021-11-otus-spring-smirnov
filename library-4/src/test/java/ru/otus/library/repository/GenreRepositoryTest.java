package ru.otus.library.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.library.model.GenreDocument;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class GenreRepositoryTest {

    private static final GenreDocument SAVED_GENRE = GenreDocument.builder()
            .id("G_TEST_ID_1")
            .name("TEST_GENRE_1")
            .build();

    private static final GenreDocument EXPECTED_GENRE = GenreDocument.builder()
            .id("G_TEST_ID_2")
            .name("TEST_GENRE_2")
            .build();

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void findByIdTest() {
        var result = genreRepository.findById(SAVED_GENRE.getId());
        assertThat(result).isPresent().get()
                .usingRecursiveComparison()
                .isEqualTo(SAVED_GENRE);
    }

    @Test
    void findAllTest() {
        var result = genreRepository.findAll();
        assertThat(result)
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(Collections.singletonList(SAVED_GENRE));
    }

    @Test
    void saveTest() {
        var result = genreRepository.save(EXPECTED_GENRE);
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(EXPECTED_GENRE);
        genreRepository.deleteById(EXPECTED_GENRE.getId());
    }

    @Test
    void deleteByIdTest() {
        var savedGenre = genreRepository.findById(SAVED_GENRE.getId());
        assertThat(savedGenre).isPresent().get()
                .usingRecursiveComparison()
                .isEqualTo(SAVED_GENRE);
        genreRepository.deleteById(SAVED_GENRE.getId());
        var result = genreRepository.findById(SAVED_GENRE.getId());
        assertThat(result)
                .isEqualTo(Optional.empty());
        genreRepository.save(savedGenre.get());
    }
}
