package ru.otus.library.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.mongo.model.AuthorDocument;

public interface AuthorMongoRepository extends MongoRepository<AuthorDocument, String> {
}
