package ru.otus.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.dto.Author;
import ru.otus.library.mapping.AuthorMapper;
import ru.otus.library.mongo.repository.AuthorMongoRepository;
import ru.otus.library.mongo.repository.BookMongoRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorMongoRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final BookMongoRepository bookRepository;

    @Override
    public Author get(String id) {
        var optEntity = authorRepository.findById(id);
        return optEntity
                .map(authorMapper::fromEntity)
                .orElse(Author.builder().build());
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Author save(Author book) {
        var savedAuthor = authorRepository.save(authorMapper.toEntity(book));
        return authorMapper.fromEntity(savedAuthor);
    }

    @Override
    public void delete(String id) {
        var books = bookRepository.findByAuthorId(id);
        if (!books.isEmpty()) {
            bookRepository.deleteAll(books);
        }
        authorRepository.deleteById(id);
    }
}
