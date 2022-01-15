package ru.otus.library.service;

import ru.otus.library.dto.Author;

import java.util.List;

public interface AuthorService {

    Author get(Long id);

    List<Author> getAll();

    Long save(Author book);
}
