package ru.otus.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.dto.Author;
import ru.otus.library.mapping.AuthorMapper;
import ru.otus.library.repository.AuthorRepository;
import ru.otus.library.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final AuthorMapper authorMapper;

    @Override
    public Author get(Long id) {
        var author = authorRepository.getById(id);
        return author != null ? authorMapper.fromEntity(author) : Author.builder().build();
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Author save(Author author) {
        return authorMapper.fromEntity(authorRepository.save(authorMapper.toEntity(author)));
    }

    @Override
    public void delete(Long id) {
        var books = bookRepository.findByAuthorId(id);
        if (!books.isEmpty()) {
            bookRepository.deleteAll(books);
        }
        authorRepository.deleteById(id);
    }
}
