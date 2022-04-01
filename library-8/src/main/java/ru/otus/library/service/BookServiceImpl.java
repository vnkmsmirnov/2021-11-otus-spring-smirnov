package ru.otus.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.dto.Book;
import ru.otus.library.mapping.BookMapper;
import ru.otus.library.model.CommentEntity;
import ru.otus.library.repository.AuthorRepository;
import ru.otus.library.repository.BookRepository;
import ru.otus.library.repository.GenreRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookMapper bookMapper;

    @Transactional
    @Override
    public Book get(Long id) {
        var optEntity = bookRepository.findById(id);
        if (optEntity.isPresent()) {
            return bookMapper.fromEntity(optEntity.get());
        }
        return Book.builder().build();
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Book save(Book book) {
        var bookEntity = bookMapper.toEntity(book);
        var author = authorRepository.findByFirstNameAndLastName(bookEntity.getAuthor().getFirstName(),
                bookEntity.getAuthor().getLastName());
        if (author == null) {
            author = authorRepository.save(bookEntity.getAuthor());
        }
        bookEntity.setAuthor(author);
        bookEntity.setAuthorId(author.getId());
        var genre = genreRepository.findByName(bookEntity.getGenre().getName());
        if (genre == null) {
            genre = genreRepository.save(bookEntity.getGenre());
        }
        bookEntity.setGenre(genre);
        bookEntity.setGenreId(genre.getId());
        return bookMapper.fromEntity(bookRepository.save(bookEntity));
    }

    @Override
    public Book addComment(Long bookId, String comment) {
        var commentEntity = CommentEntity.builder()
                .text(comment)
                .build();
        return Optional.of(bookRepository.findById(bookId))
                .map(Optional::get).map(b -> {
                    if (b.getComments() == null) {
                        b.setComments(List.of(commentEntity));
                    } else {
                        b.getComments().add(commentEntity);
                    }
                    return bookMapper.fromEntity(bookRepository.save(b));
                })
                .orElse(Book.builder().build());
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
