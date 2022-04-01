package ru.otus.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.library.model.AuthorEntity;

import java.util.List;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    @PostAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    AuthorEntity getById(Long id);

    @PostAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    List<AuthorEntity> findAll();

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    AuthorEntity save(@Param("authorEntity")AuthorEntity authorEntity);

    @PostAuthorize("hasAuthority('ROLE_ADMIN')")
    AuthorEntity findByFirstNameAndLastName(String firstName, String lastName);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    void deleteById(Long id);
}
