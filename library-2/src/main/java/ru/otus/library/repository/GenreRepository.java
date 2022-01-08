package ru.otus.library.repository;

import ru.otus.library.model.GenreEntity;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    Optional<GenreEntity> findById(Long id);

    GenreEntity findByName(String name);

    List<GenreEntity> findAll();

    Long save(GenreEntity genre);
}
