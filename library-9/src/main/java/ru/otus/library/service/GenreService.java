package ru.otus.library.service;

import ru.otus.library.dto.Genre;

import java.util.List;

public interface GenreService {

    Genre get(String id);

    List<Genre> getAll();

    Genre save(Genre book);

    void delete(String id);
}
