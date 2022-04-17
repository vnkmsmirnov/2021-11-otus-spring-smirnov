package ru.otus.library.mapping;

import ru.otus.library.dto.Book;
import ru.otus.library.model.BookEntity;

public interface BookMapper {

    Book fromEntity(BookEntity entity);

    BookEntity toEntity(Book book);
}
