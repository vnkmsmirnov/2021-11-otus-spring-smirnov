package ru.otus.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.dao.GenreDao;
import ru.otus.library.dto.Genre;
import ru.otus.library.mapping.GenreMapper;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDao;
    private final GenreMapper genreMapper;

    @Override
    public Genre get(Long id) {
        return genreMapper.fromEntity(genreDao.findById(id));
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.findAll()
                .stream()
                .map(genreMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Long save(Genre genre) {
        return genreDao.save(genreMapper.toEntity(genre));
    }
}
