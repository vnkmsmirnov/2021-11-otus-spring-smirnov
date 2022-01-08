package ru.otus.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.dto.Author;
import ru.otus.library.mapping.AuthorMapper;
import ru.otus.library.repository.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public Author get(Long id) {
        var optEntity = authorRepository.findById(id);
        if (optEntity.isPresent()) {
            return authorMapper.fromEntity(optEntity.get());
        }
        return Author.builder().build();
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Long save(Author author) {
        var entity = authorMapper.toEntity(author);
        var result = authorRepository.findByFirstNameAndLastName(entity.getFirstName(), entity.getLastName());
        if (result != null) {
            entity.setId(result.getId());
        }
        return authorRepository.save(entity);
    }
}
