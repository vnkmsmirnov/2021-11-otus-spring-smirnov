package ru.otus.library.mapping;

import org.springframework.stereotype.Component;
import ru.otus.library.dto.Genre;
import ru.otus.library.model.GenreEntity;

@Component
public class GenreMapperImpl implements GenreMapper {

    @Override
    public Genre fromEntity(GenreEntity entity) {
        return Genre.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public GenreEntity toEntity(Genre genre) {
        return GenreEntity.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
}
