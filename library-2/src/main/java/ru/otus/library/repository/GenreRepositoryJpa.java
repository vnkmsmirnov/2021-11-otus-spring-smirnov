package ru.otus.library.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.model.GenreEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class GenreRepositoryJpa implements GenreRepository {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<GenreEntity> findById(Long id) {
        return Optional.ofNullable(em.find(GenreEntity.class, id));
    }

    @Override
    public GenreEntity findByName(String name) {
        var query = em.createQuery("select g from GenreEntity g where g.name = :name", GenreEntity.class);
        query.setParameter("name", name);
        GenreEntity result = null;
        try {
            result = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Genre entity not found");
        }
        return result;
    }

    @Override
    public List<GenreEntity> findAll() {
        return em.createQuery("select g from GenreEntity g order by g.id",
                GenreEntity.class).getResultList();
    }

    @Transactional
    @Override
    public Long save(GenreEntity genre) {
        if (genre.getId() == null || genre.getId() == 0) {
            em.persist(genre);
            return genre.getId();
        } else {
            return em.merge(genre).getId();
        }
    }
}
