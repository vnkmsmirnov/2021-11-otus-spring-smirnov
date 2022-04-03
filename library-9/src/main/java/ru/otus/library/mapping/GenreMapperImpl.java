package ru.otus.library.mapping;

import org.springframework.stereotype.Component;
import ru.otus.library.dto.Genre;
import ru.otus.library.mongo.model.GenreDocument;

import java.util.Optional;

@Component
public class GenreMapperImpl implements GenreMapper {

    @Override
    public Genre fromEntity(GenreDocument entity) {
        var genre = Optional.ofNullable(entity)
                .orElse(GenreDocument.builder().build());
        return Genre.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }

    @Override
    public GenreDocument toEntity(Genre genre) {
        return GenreDocument.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
}
