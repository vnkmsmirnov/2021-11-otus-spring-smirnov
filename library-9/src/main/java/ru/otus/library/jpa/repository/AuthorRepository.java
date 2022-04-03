package ru.otus.library.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.jpa.model.AuthorEntity;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    AuthorEntity findByFirstNameAndLastName(String firstName, String lastName);
}
