package ru.otus.library.repository;

import ru.otus.library.model.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    Optional<AuthorEntity> findById(Long id);

    AuthorEntity findByFirstNameAndLastName(String firstName, String lastName);

    List<AuthorEntity> findAll();

    Long save(AuthorEntity author);
}
