package ru.otus.library.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.library.model.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @PostAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @EntityGraph(value = "books-entity-graph")
    Optional<BookEntity> findById(Long id);

    @PostAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @EntityGraph(value = "books-entity-graph")
    List<BookEntity> findAll();

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    BookEntity save(BookEntity bookEntity);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    List<BookEntity> findByAuthorId(Long authorId);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    void deleteById(Long id);
}
