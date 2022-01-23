package ru.otus.library.service;

import ru.otus.library.dto.Comment;

import java.util.List;

public interface CommentService {

    Comment get(String id);

    List<Comment> getByBookId(String id);

    List<Comment> getAll();

    String save(Comment comment);

    void delete(String id);
}
