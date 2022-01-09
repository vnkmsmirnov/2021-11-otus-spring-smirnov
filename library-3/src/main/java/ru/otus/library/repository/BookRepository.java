package ru.otus.library.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.model.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @EntityGraph(value = "books-entity-graph")
    Optional<BookEntity> findById(Long id);

    @EntityGraph(value = "books-entity-graph")
    List<BookEntity> findAll();
}
