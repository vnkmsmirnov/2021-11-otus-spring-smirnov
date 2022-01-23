package ru.otus.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.dto.Book;
import ru.otus.library.mapping.BookMapper;
import ru.otus.library.repository.BookRepository;
import ru.otus.library.repository.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final CommentRepository commentRepository;

    @Override
    public Book get(String id) {
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
                .map(bookMapper::withoutCommentsFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public String save(Book book) {
        return bookRepository.save(bookMapper.toEntity(book)).getId();
    }

    @Override
    public void delete(String id) {
        commentRepository.deleteByBookId(id);
        bookRepository.deleteById(id);
    }
}
