package ru.otus.library.service;

import ru.otus.library.dto.Book;

import java.util.List;

public interface BookService {

    Book get(Long id);

    List<Book> getAll();

    Book save(Book book);

    Book addComment(Long bookId, String comment);

    void delete(Long id);
}
