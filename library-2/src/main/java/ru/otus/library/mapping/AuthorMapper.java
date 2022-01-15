package ru.otus.library.mapping;

import ru.otus.library.dto.Author;
import ru.otus.library.model.AuthorEntity;

public interface AuthorMapper {

    Author fromEntity(AuthorEntity entity);

    AuthorEntity toEntity(Author author);
}
