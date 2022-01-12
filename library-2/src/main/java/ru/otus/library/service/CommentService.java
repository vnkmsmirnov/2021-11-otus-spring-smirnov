package ru.otus.library.service;

import ru.otus.library.dto.Comment;

import java.util.List;

public interface CommentService {

    Comment get(Long id);

    List<Comment> getByBookId(Long id);

    List<Comment> getAll();

    Long save(Comment comment);

    void delete(Long id);
}
