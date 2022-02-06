package ru.otus.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.model.AuthorDocument;

public interface AuthorRepository extends MongoRepository<AuthorDocument, String> {
}
