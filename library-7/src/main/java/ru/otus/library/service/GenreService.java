package ru.otus.library.service;

import ru.otus.library.dto.Genre;

import java.util.List;

public interface GenreService {

    Genre get(Long id);

    List<Genre> getAll();

    Genre save(Genre genre);

    void delete(Long id);
}
