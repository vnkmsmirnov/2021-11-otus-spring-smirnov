package ru.otus.library.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.library.model.BookEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<BookEntity> findById(Long id) {
        return Optional.ofNullable(em.find(BookEntity.class, id));
    }

    @Override
    public List<BookEntity> findAll() {
        var entityGraph = em.getEntityGraph("books-entity-graph");
        var query =  em.createQuery("select b from BookEntity b order by b.id", BookEntity.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public Long save(BookEntity book) {
        if (book.getId() == null || book.getId() == 0) {
            em.persist(book);
            return book.getId();
        } else {
            return em.merge(book).getId();
        }
    }

    @Override
    public void deleteById(Long id) {
        var book = em.merge(BookEntity.builder().id(id).build());
        em.remove(book);
    }
}