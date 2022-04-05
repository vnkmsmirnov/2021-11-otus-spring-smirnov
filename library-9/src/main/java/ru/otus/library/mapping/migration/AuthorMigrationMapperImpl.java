package ru.otus.library.mapping.migration;

import org.springframework.stereotype.Component;
import ru.otus.library.jpa.model.AuthorEntity;
import ru.otus.library.mongo.model.AuthorDocument;

import java.util.Optional;

@Component
public class AuthorMigrationMapperImpl implements AuthorMigrationMapper {

    @Override
    public AuthorEntity map(AuthorDocument document) {
        var fullName = Optional.ofNullable(document)
                .map(a -> a.getName().split(" "))
                .orElse(new String[]{"-", "-"});
        return AuthorEntity.builder()
                .firstName(fullName[0])
                .lastName(fullName.length > 1 ? fullName[1] : "-")
                .build();
    }
}
