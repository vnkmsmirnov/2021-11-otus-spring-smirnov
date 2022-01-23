package ru.otus.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.library.model.CommentDocument;

import java.util.List;

public interface CommentRepository extends MongoRepository<CommentDocument, String> {

    @Query(value = "{'book_id': ?0}")
    List<CommentDocument> getByBookId(@Param("id") String id);

    void deleteByBookId(@Param("bookId") String bookId);
}
