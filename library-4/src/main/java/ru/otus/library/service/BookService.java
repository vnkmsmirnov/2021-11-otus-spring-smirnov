package ru.otus.library.service;

import ru.otus.library.dto.Book;

import java.util.List;

public interface BookService {

    Book get(String id);

    List<Book> getAll();

    String save(Book book);

    void delete(String id);
}
