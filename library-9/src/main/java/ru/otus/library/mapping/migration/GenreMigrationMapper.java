package ru.otus.library.mapping.migration;

import ru.otus.library.jpa.model.GenreEntity;
import ru.otus.library.mongo.model.GenreDocument;

public interface GenreMigrationMapper {

    GenreEntity map(GenreDocument document);
}
