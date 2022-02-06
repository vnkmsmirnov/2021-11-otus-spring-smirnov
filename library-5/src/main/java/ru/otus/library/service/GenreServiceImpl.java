package ru.otus.library.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.otus.library.dto.Genre;
import ru.otus.library.mapping.GenreMapper;
import ru.otus.library.repository.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final SequenceService sequenceService;
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public Genre get(String id) {
        var optEntity = genreRepository.findById(id);
        return optEntity
                .map(genreMapper::fromEntity)
                .orElse(Genre.builder().build());
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
        if (StringUtils.isEmpty(genre.getId())) {
            genre.setId(sequenceService.get(SequencePrefix.GENRE));
        }
        var result = genreRepository.save(genreMapper.toEntity(genre));
        return genreMapper.fromEntity(result);
    }

    @Override
    public void delete(String id) {
        genreRepository.deleteById(id);
    }
}
