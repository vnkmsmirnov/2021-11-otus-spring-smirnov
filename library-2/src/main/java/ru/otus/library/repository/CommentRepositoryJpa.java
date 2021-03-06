package ru.otus.library.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.library.model.CommentEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<CommentEntity> findById(Long id) {
        return Optional.ofNullable(em.find(CommentEntity.class, id));
    }

    @Override
    public List<CommentEntity> findAll() {
        return em.createQuery("select c from CommentEntity c order by c.id",
                CommentEntity.class).getResultList();
    }

    @Override
    public Long save(CommentEntity comment) {
        if (comment.getId() == null || comment.getId() == 0) {
            em.persist(comment);
            return comment.getId();
        } else {
            return em.merge(comment).getId();
        }
    }

    @Override
    public void deleteById(Long id) {
        var comment = em.merge(CommentEntity.builder()
                .id(id)
                .build());
        em.remove(comment);
    }
}
