package ru.otus.library.service;

import ru.otus.library.dto.Author;

import java.util.List;

public interface AuthorService {

    Author get(String id);

    List<Author> getAll();

    Author save(Author book);

    void delete(String id);
}
