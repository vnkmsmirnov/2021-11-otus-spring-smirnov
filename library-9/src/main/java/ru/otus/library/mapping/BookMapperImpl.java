package ru.otus.library.mapping;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.library.dto.Book;
import ru.otus.library.mongo.model.BookDocument;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class BookMapperImpl implements BookMapper {

    private final GenreMapper genreMapper;
    private final AuthorMapper authorMapper;
    private final CommentMapper commentMapper;

    @Override
    public Book fromEntity(BookDocument entity) {
        return Book.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .pages(entity.getPages())
                .genre(genreMapper.fromEntity(entity.getGenre()))
                .author(authorMapper.fromEntity(entity.getAuthor()))
                .comments(Optional.ofNullable(entity.getComments())
                        .stream()
                        .flatMap(Collection::stream)
                        .map(commentMapper::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public BookDocument toEntity(Book book) {
        return BookDocument.builder()
                .id(book.getId())
                .title(book.getTitle())
                .pages(book.getPages())
                .genre(genreMapper.toEntity(book.getGenre()))
                .author(authorMapper.toEntity(book.getAuthor()))
                .comments(Optional.ofNullable(book.getComments())
                        .stream()
                        .flatMap(Collection::stream)
                        .map(commentMapper::toEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}
