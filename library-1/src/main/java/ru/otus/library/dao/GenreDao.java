package ru.otus.library.dao;

import ru.otus.library.model.GenreEntity;

import java.util.List;

public interface GenreDao {

    GenreEntity findById(Long id);

    List<GenreEntity> findAll();

    Long save(GenreEntity author);
}
