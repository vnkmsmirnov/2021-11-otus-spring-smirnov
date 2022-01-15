package ru.otus.library.mapping;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.library.dto.Book;
import ru.otus.library.model.BookEntity;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class BookMapperImpl implements BookMapper {
    private final GenreMapper genreMapper;
    private final AuthorMapper authorMapper;
    private final CommentMapper commentMapper;

    @Override
    public Book byIdFromEntity(BookEntity entity) {
        return Book.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .pages(entity.getPages())
                .genre(genreMapper.fromEntity(entity.getGenre()))
                .author(authorMapper.fromEntity(entity.getAuthor()))
                .comments(entity.getComments().stream()
                        .map(commentMapper::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public Book allFromEntity(BookEntity entity) {
        return Book.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .pages(entity.getPages())
                .genre(genreMapper.fromEntity(entity.getGenre()))
                .author(authorMapper.fromEntity(entity.getAuthor()))
                .build();
    }

    @Override
    public BookEntity toEntity(Book book) {
        return BookEntity.builder()
                .id(book.getId())
                .title(book.getTitle())
                .pages(book.getPages())
                .genre(genreMapper.toEntity(book.getGenre()))
                .author(authorMapper.toEntity(book.getAuthor()))
                .build();
    }
}
