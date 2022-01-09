package ru.otus.library.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.dto.Book;
import ru.otus.library.mapping.BookMapper;
import ru.otus.library.repository.AuthorRepository;
import ru.otus.library.repository.BookRepository;
import ru.otus.library.repository.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final BookMapper bookMapper;

    @Override
    public Book get(Long id) {
        var optEntity = bookRepository.findById(id);
        if (optEntity.isPresent()) {
            return bookMapper.byIdFromEntity(optEntity.get());
        }
        return Book.builder().build();
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::allFromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Long save(Book book) {
        var entity = bookMapper.toEntity(book);
        val authorFirstName = entity.getAuthor().getFirstName();
        val authorLastName = entity.getAuthor().getLastName();
        var authorEntity = authorRepository.findByFirstNameAndLastName(authorFirstName, authorLastName);
        if (authorEntity != null) {
            entity.getAuthor().setId(authorEntity.getId());
        }
        val genreName = entity.getGenre().getName();
        var genreEntity = genreRepository.findByName(genreName);
        if (genreEntity != null) {
            entity.getGenre().setId(genreEntity.getId());
        }
        var authorId = authorRepository.save(entity.getAuthor());
        var genreId = genreRepository.save(entity.getGenre());
        entity.setAuthorId(authorId);
        entity.setGenreId(genreId);
        return bookRepository.save(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
