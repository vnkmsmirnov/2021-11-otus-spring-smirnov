package ru.otus.library.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.library.model.AuthorEntity;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AuthorRepositoryTest {
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
    private AuthorRepository authorRepository;

    @Test
    void saveTest() {
        var result = authorRepository.save(EXPECTED_AUTHOR);
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(EXPECTED_AUTHOR);
    }

    @Test
    void findByIdTest() {
        var result = authorRepository.findById(SAVED_AUTHOR.getId());
        assertThat(result).isPresent().get()
                .usingRecursiveComparison()
                .isEqualTo(SAVED_AUTHOR);
    }

    @Test
    void findByFirstNameAndLastNameTest() {
        var result = authorRepository.findByFirstNameAndLastName(SAVED_AUTHOR.getFirstName(), SAVED_AUTHOR.getLastName());
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(SAVED_AUTHOR);
    }

    @Test
    void findAllTest() {
        var result = authorRepository.findAll();
        assertThat(result)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(SAVED_AUTHOR);
    }
}