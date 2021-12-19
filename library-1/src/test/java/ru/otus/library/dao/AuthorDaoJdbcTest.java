package ru.otus.library.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.model.AuthorEntity;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {
    private static final AuthorEntity SAVED_AUTHOR = AuthorEntity.builder()
            .id(1L)
            .firstName("FIRST_NAME_1")
            .lastName("LAST_NAME_1")
            .build();

    private static final AuthorEntity EXPECTED_AUTHOR = AuthorEntity.builder()
            .id(2L)
            .firstName("FIRST_NAME_2")
            .lastName("LAST_NAME_2")
            .build();

    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @Test
    void findByIdTest() {
        var result = authorDaoJdbc.findById(SAVED_AUTHOR.getId());
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(SAVED_AUTHOR);
    }

    @Test
    void findAllTest() {
        var result = authorDaoJdbc.findAll();
        assertThat(result)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(SAVED_AUTHOR);
    }

    @Test
    void saveTest() {
        authorDaoJdbc.save(EXPECTED_AUTHOR);
        var result = authorDaoJdbc.findById(EXPECTED_AUTHOR.getId());
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(EXPECTED_AUTHOR);
    }
}