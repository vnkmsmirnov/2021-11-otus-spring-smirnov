package ru.otus.library.service.migration;

import ru.otus.library.jpa.model.BookEntity;
import ru.otus.library.mongo.model.BookDocument;

public interface LibraryMigrationService {

    BookEntity prepareBook(BookDocument document);
}
