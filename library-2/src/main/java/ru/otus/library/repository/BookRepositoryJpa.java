package ru.otus.library.repository;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

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

    @Transactional
    @Override
    public Long save(BookEntity book) {
        val authorFirstName = book.getAuthor().getFirstName();
        val authorLastName = book.getAuthor().getLastName();
        var authorEntity = authorRepository.findByFirstNameAndLastName(authorFirstName, authorLastName);
        if (authorEntity != null) {
            book.getAuthor().setId(authorEntity.getId());
        }
        val genreName = book.getGenre().getName();
        var genreEntity = genreRepository.findByName(genreName);
        if (genreEntity != null) {
            book.getGenre().setId(genreEntity.getId());
        }
        var authorId = authorRepository.save(book.getAuthor());
        var genreId = genreRepository.save(book.getGenre());
        book.setAuthorId(authorId);
        book.setGenreId(genreId);
        if (book.getId() == null || book.getId() == 0) {
            em.persist(book);
            return book.getId();
        } else {
            return em.merge(book).getId();
        }
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        var query = em.createQuery("delete from BookEntity book where book.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}