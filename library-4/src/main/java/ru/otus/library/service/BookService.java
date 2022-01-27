package ru.otus.library.service;

import ru.otus.library.dto.Book;

import java.util.List;

public interface BookService {

    Book get(String id);

    List<Book> getAll();

    Book save(Book book);

    Book addComment(String bookId, String comment);

    void delete(String id);
}
