package ru.otus.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.dao.BookDao;
import ru.otus.library.dto.Book;
import ru.otus.library.mapping.BookMapper;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final BookMapper bookMapper;

    @Override
    public Book get(Long id) {
        return bookMapper.fromEntity(bookDao.findById(id));
    }

    @Override
    public List<Book> getAll() {
        return bookDao.findAll()
                .stream()
                .map(bookMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Long save(Book book) {
        return bookDao.save(bookMapper.toEntity(book));
    }

    @Override
    public void delete(Long id) {
        bookDao.deleteById(id);
    }
}
