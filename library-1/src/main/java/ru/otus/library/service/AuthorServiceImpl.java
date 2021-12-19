package ru.otus.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.dao.AuthorDao;
import ru.otus.library.dto.Author;
import ru.otus.library.mapping.AuthorMapper;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;
    private final AuthorMapper authorMapper;

    @Override
    public Author get(Long id) {
        return authorMapper.fromEntity(authorDao.findById(id));
    }

    @Override
    public List<Author> getAll() {
        return authorDao.findAll()
                .stream()
                .map(authorMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Long save(Author book) {
        return authorDao.save(authorMapper.toEntity(book));
    }
}
