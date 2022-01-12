package ru.otus.library.repository;

import ru.otus.library.model.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Optional<BookEntity> findById(Long id);

    List<BookEntity> findAll();

    Long save(BookEntity author);

    void deleteById(Long id);
}
