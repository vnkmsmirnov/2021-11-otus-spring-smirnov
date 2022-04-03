package ru.otus.library.mapping.migration;

import ru.otus.library.jpa.model.CommentEntity;
import ru.otus.library.mongo.model.CommentDocument;

public interface CommentMigrationMapper {

    CommentEntity map(CommentDocument document);
}
