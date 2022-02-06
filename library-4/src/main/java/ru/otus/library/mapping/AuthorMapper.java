package ru.otus.library.mapping;

import ru.otus.library.dto.Author;
import ru.otus.library.model.AuthorDocument;

public interface AuthorMapper {

    Author fromEntity(AuthorDocument entity);

    AuthorDocument toEntity(Author author);
}
