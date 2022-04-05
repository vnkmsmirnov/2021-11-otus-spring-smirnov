package ru.otus.library.mapping;

import org.springframework.stereotype.Component;
import ru.otus.library.dto.Author;
import ru.otus.library.mongo.model.AuthorDocument;

import java.util.Optional;

@Component
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public Author fromEntity(AuthorDocument entity) {
        var author = Optional.ofNullable(entity)
                .orElse(AuthorDocument.builder().build());
        return Author.builder()
                .id(author.getId())
                .name(author.getName())
                .build();
    }

    @Override
    public AuthorDocument toEntity(Author author) {
        return AuthorDocument.builder()
                .id(author.getId())
                .name(author.getName())
                .build();
    }
}
