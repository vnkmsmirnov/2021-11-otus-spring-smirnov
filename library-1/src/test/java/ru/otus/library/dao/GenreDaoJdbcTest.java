package ru.otus.library.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.model.GenreEntity;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {
    private static final GenreEntity SAVED_GENRE = GenreEntity.builder()
            .id(1L)
            .name("TEST_GENRE_1")
            .build();

    private static final GenreEntity EXPECTED_GENRE = GenreEntity.builder()
            .id(2L)
            .name("TEST_GENRE_2")
            .build();

    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    @Test
    void findByIdTest() {
        var result = genreDaoJdbc.findById(SAVED_GENRE.getId());
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(SAVED_GENRE);
    }

    @Test
    void findAllTest() {
        var result = genreDaoJdbc.findAll();
        assertThat(result)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(SAVED_GENRE);
    }

    @Test
    void saveTest() {
        genreDaoJdbc.save(EXPECTED_GENRE);
        var result = genreDaoJdbc.findById(EXPECTED_GENRE.getId());
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(EXPECTED_GENRE);
    }
}