package ru.otus.library.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.mongo.model.GenreDocument;

public interface GenreMongoRepository extends MongoRepository<GenreDocument, String> {
}
