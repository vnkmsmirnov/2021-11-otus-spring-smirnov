package ru.otus.library.mapping;

import org.springframework.stereotype.Component;
import ru.otus.library.dto.Author;
import ru.otus.library.model.AuthorEntity;

@Component
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public Author fromEntity(AuthorEntity entity) {
        return Author.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .build();
    }

    @Override
    public AuthorEntity toEntity(Author author) {
        return AuthorEntity.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .build();
    }
}
