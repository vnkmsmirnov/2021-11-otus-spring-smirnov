package ru.otus.library.mapping.migration;

import org.springframework.stereotype.Component;
import ru.otus.library.jpa.model.GenreEntity;
import ru.otus.library.mongo.model.GenreDocument;

import java.util.Optional;

@Component
public class GenreMigrationMapperImpl implements GenreMigrationMapper {

    @Override
    public GenreEntity map(GenreDocument document) {
        return GenreEntity.builder()
                .name(Optional.ofNullable(document)
                        .map(GenreDocument::getName)
                        .orElse("-"))
                .build();
    }
}
