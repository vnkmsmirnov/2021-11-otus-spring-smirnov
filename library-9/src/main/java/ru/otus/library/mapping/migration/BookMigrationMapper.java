package ru.otus.library.mapping.migration;

import ru.otus.library.jpa.model.BookEntity;
import ru.otus.library.mongo.model.BookDocument;

public interface BookMigrationMapper {

    BookEntity map(BookDocument document);
}
