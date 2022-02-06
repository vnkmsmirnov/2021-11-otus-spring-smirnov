package ru.otus.library.mapping;

import ru.otus.library.dto.Book;
import ru.otus.library.model.BookEntity;

public interface BookMapper {

    Book byIdFromEntity(BookEntity entity);

    Book allFromEntity(BookEntity entity);

    BookEntity toEntity(Book book);
}
