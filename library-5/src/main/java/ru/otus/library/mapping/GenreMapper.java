package ru.otus.library.mapping;

import ru.otus.library.dto.Genre;
import ru.otus.library.model.GenreDocument;

public interface GenreMapper {

    Genre fromEntity(GenreDocument entity);

    GenreDocument toEntity(Genre genre);
}
