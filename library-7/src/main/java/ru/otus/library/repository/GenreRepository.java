package ru.otus.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.model.GenreEntity;

public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

    GenreEntity findByName(String name);
}
