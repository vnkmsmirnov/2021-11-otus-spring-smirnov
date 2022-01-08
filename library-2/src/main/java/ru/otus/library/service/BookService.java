package ru.otus.library.service;

import ru.otus.library.dto.Book;

import java.util.List;

public interface BookService {

    Book get(Long id);

    List<Book> getAll();

    Long save(Book book);

    void delete(Long id);
}
