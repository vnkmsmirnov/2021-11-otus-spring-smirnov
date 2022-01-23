package ru.otus.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.model.BookDocument;

public interface BookRepository extends MongoRepository<BookDocument, String> {
}
