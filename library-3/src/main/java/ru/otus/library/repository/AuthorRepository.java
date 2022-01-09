package ru.otus.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.model.AuthorEntity;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    AuthorEntity findByFirstNameAndLastName(String firstName, String lastName);
}
