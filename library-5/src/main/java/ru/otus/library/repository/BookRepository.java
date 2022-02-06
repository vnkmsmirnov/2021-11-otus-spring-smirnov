package ru.otus.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.library.model.BookDocument;


import java.util.List;

public interface BookRepository extends MongoRepository<BookDocument, String> {

    List<BookDocument> findByAuthorId(@Param("id") String authorId);

    List<BookDocument> findByGenreId(@Param("id") String genreId);
}
