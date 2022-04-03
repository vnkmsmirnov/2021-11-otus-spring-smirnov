package ru.otus.library.mapping;

import ru.otus.library.dto.Book;
import ru.otus.library.mongo.model.BookDocument;

public interface BookMapper {

    Book fromEntity(BookDocument entity);

    BookDocument toEntity(Book book);
}
