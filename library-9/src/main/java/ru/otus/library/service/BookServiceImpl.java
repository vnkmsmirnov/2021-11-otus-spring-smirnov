package ru.otus.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.dto.Book;
import ru.otus.library.mapping.BookMapper;
import ru.otus.library.mongo.model.CommentDocument;
import ru.otus.library.mongo.repository.BookMongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final MongoSequenceService sequenceService;
    private final BookMongoRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookMapper bookMapper;

    @Override
    public Book get(String id) {
        var optEntity = bookRepository.findById(id);
        return optEntity
                .map(bookMapper::fromEntity)
                .orElse(Book.builder().build());
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
        book.setGenre(genreService.save(book.getGenre()));
        book.setAuthor(authorService.save(book.getAuthor()));
        var savedBook = bookRepository.save(bookMapper.toEntity(book));
        return bookMapper.fromEntity(savedBook);
    }

    @Override
    public Book addComment(String bookId, String comment) {
        var commentDoc = CommentDocument.builder()
                .id(sequenceService.getSequence(SequencePrefix.COMMENT))
                .text(comment)
                .build();
        return Optional.of(bookRepository.findById(bookId))
                .map(Optional::get).map(b -> {
                    if (b.getComments() == null) {
                        b.setComments(List.of(commentDoc));
                    } else {
                        b.getComments().add(commentDoc);
                    }
                    return bookMapper.fromEntity(bookRepository.save(b));
                })
                .orElse(Book.builder().build());
    }

    @Override
    public void delete(String id) {
        bookRepository.deleteById(id);
    }
}
