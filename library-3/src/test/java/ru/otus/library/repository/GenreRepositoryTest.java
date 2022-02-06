package ru.otus.library.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.library.model.GenreEntity;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class GenreRepositoryTest {
    private static final GenreEntity SAVED_GENRE = GenreEntity.builder()
            .id(1L)
            .name("TEST_GENRE_1")
            .build();

    private static final GenreEntity EXPECTED_GENRE = GenreEntity.builder()
            .id(2L)
            .name("TEST_GENRE_2")
            .build();

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void findByIdTest() {
        var result = genreRepository.findById(SAVED_GENRE.getId());
        assertThat(result).isPresent().get()
                .usingRecursiveComparison()
                .isEqualTo(SAVED_GENRE);
    }

    @Test
    void findByNameTest() {
        var result = genreRepository.findByName(SAVED_GENRE.getName());
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(SAVED_GENRE);
    }

    @Test
    void findAllTest() {
        var result = genreRepository.findAll();
        assertThat(result)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(SAVED_GENRE);
    }

    @Test
    void saveTest() {
        var result = genreRepository.save(EXPECTED_GENRE);
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(EXPECTED_GENRE);
    }
}