package ru.otus.library.mapping.migration;

import ru.otus.library.jpa.model.AuthorEntity;
import ru.otus.library.mongo.model.AuthorDocument;

public interface AuthorMigrationMapper {

    AuthorEntity map(AuthorDocument document);
}
