package ru.otus.library.repository;

import ru.otus.library.model.CommentEntity;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Optional<CommentEntity> findById(Long id);

    List<CommentEntity> findByBookId(Long id);

    List<CommentEntity> findAll();

    Long save(CommentEntity author);

    void deleteById(Long id);
}
