package ru.otus.library.mapping;

import org.springframework.stereotype.Component;
import ru.otus.library.dto.Genre;
import ru.otus.library.model.GenreDocument;

@Component
public class GenreMapperImpl implements GenreMapper {

    @Override
    public Genre fromEntity(GenreDocument entity) {
        return Genre.builder()
                .name(entity.getName())
                .build();
    }

    @Override
    public GenreDocument toEntity(Genre genre) {
        return GenreDocument.builder()
                .name(genre.getName())
                .build();
    }
}
