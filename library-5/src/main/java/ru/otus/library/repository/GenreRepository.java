package ru.otus.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.model.GenreDocument;

public interface GenreRepository extends MongoRepository<GenreDocument, String> {
}
