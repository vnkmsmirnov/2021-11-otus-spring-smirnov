package ru.otus.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    @Override
    public Genre save(Genre genre) {
        return genreMapper.fromEntity(genreRepository.save(genreMapper.toEntity(genre)));
    }

    @Override
    public void delete(Long id) {
        genreRepository.deleteById(id);
    }
}
