package ru.otus.library.mapping.migration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.library.jpa.model.BookEntity;
import ru.otus.library.mongo.model.BookDocument;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class BookMigrationMapperImpl implements BookMigrationMapper {

    private final GenreMigrationMapper genreMapper;
    private final AuthorMigrationMapper authorMapper;
    private final CommentMigrationMapper commentMapper;

    @Override
    public BookEntity map(BookDocument document) {
        return BookEntity.builder()
                .title(document.getTitle())
                .pages(document.getPages())
                .genre(genreMapper.map(document.getGenre()))
                .author(authorMapper.map(document.getAuthor()))
                .comments(Optional.ofNullable(document.getComments())
                        .stream()
                        .flatMap(Collection::stream)
                        .map(commentMapper::map)
                        .collect(Collectors.toList()))
                .build();
    }
}
