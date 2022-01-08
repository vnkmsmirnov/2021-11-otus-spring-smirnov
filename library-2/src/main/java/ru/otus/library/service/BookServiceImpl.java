package ru.otus.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.dto.Book;
import ru.otus.library.mapping.BookMapper;
import ru.otus.library.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
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

    @Override
    public Long save(Book book) {
        return bookRepository.save(bookMapper.toEntity(book));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
