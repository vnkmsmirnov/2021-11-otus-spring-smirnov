package ru.otus.library.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.model.CommentEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CommentRepositoryJpa implements CommentRepository {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<CommentEntity> findById(Long id) {
        return Optional.ofNullable(em.find(CommentEntity.class, id));
    }

    @Override
    public List<CommentEntity> findByBookId(Long id) {
        var query = em.createQuery("select c from CommentEntity c where c.bookId = :id order by c.id",
                CommentEntity.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<CommentEntity> findAll() {
        return em.createQuery("select c from CommentEntity c order by c.id",
                CommentEntity.class).getResultList();
    }

    @Transactional
    @Override
    public Long save(CommentEntity comment) {
        if (comment.getId() == null || comment.getId() == 0) {
            em.persist(comment);
            return comment.getId();
        } else {
            return em.merge(comment).getId();
        }
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        var query = em.createQuery("delete from CommentEntity comment where comment.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
