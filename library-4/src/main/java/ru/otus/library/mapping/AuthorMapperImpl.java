package ru.otus.library.mapping;

import org.springframework.stereotype.Component;
import ru.otus.library.dto.Author;
import ru.otus.library.model.AuthorDocument;

@Component
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public Author fromEntity(AuthorDocument entity) {
        return Author.builder()
                .name(entity.getName())
                .build();
    }

    @Override
    public AuthorDocument toEntity(Author author) {
        return AuthorDocument.builder()
                .name(author.getName())
                .build();
    }
}
