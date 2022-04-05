package ru.otus.library.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.jpa.model.GenreEntity;

public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

    GenreEntity findByName(String name);
}