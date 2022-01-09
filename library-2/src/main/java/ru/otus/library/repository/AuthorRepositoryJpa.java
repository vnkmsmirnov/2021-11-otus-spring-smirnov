package ru.otus.library.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.model.AuthorEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<AuthorEntity> findById(Long id) {
        return Optional.ofNullable(em.find(AuthorEntity.class, id));
    }

    @Override
    public AuthorEntity findByFirstNameAndLastName(String firstName, String lastName) {
        var query = em.createQuery("select a from AuthorEntity a where a.firstName = :firstName and a.lastName = :lastName",
                AuthorEntity.class);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        AuthorEntity result = null;
        try {
            result = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Author entity not found");
        }
        return result;
    }

    @Override
    public List<AuthorEntity> findAll() {
        return em.createQuery("select a from AuthorEntity a order by a.id",
                AuthorEntity.class).getResultList();
    }

    @Transactional
    @Override
    public Long save(AuthorEntity author) {
        if (author.getId() == null || author.getId() == 0) {
            em.persist(author);
            return author.getId();
        } else {
            return em.merge(author).getId();
        }
    }
}
