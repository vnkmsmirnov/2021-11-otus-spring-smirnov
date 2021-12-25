package ru.otus.library.dao;

import ru.otus.library.model.BookEntity;

import java.util.List;

public interface BookDao {

    BookEntity findById(Long id);

    List<BookEntity> findAll();

    Long save(BookEntity author);

    void deleteById(Long id);
}
