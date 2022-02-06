package ru.otus.library.mapping;

import ru.otus.library.dto.Comment;
import ru.otus.library.model.CommentDocument;

public interface CommentMapper {

    Comment fromEntity(CommentDocument entity);

    CommentDocument toEntity(Comment comment);
}
