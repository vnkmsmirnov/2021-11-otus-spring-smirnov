package ru.otus.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.library.model.GenreEntity;

import java.util.List;

public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

    @PostAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    GenreEntity getById(Long id);

    @PostAuthorize("hasAuthority('ROLE_ADMIN')")
    GenreEntity findByName(String name);

    @PostAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    List<GenreEntity> findAll();

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    GenreEntity save(GenreEntity genreEntity);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    void deleteById(Long id);
}
