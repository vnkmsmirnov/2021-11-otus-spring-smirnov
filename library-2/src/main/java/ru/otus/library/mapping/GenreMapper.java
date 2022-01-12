package ru.otus.library.mapping;

import ru.otus.library.dto.Genre;
import ru.otus.library.model.GenreEntity;

public interface GenreMapper {

    Genre fromEntity(GenreEntity entity);

    GenreEntity toEntity(Genre genre);
}
