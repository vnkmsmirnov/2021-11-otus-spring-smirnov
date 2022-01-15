package ru.otus.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.dto.Genre;
import ru.otus.library.mapping.GenreMapper;
import ru.otus.library.repository.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    private final GenreMapper genreMapper;

    @Override
    public Genre get(Long id) {
        var optEntity = genreRepository.findById(id);
        if (optEntity.isPresent()) {
            return genreMapper.fromEntity(optEntity.get());
        }
        return Genre.builder().build();
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll()
                .stream()
                .map(genreMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Long save(Genre genre) {
        var entity = genreMapper.toEntity(genre);
        var result = genreRepository.findByName(entity.getName());
        if (result != null) {
            entity.setId(result.getId());
        }
        return genreRepository.save(entity);
    }
}
