package ru.otus.library.mapping;

import ru.otus.library.dto.Comment;
import ru.otus.library.model.CommentEntity;

public interface CommentMapper {

    Comment fromEntity(CommentEntity entity);

    CommentEntity toEntity(Comment comment);
}
