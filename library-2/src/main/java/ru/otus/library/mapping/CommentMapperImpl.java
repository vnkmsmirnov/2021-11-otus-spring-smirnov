package ru.otus.library.mapping;

import org.springframework.stereotype.Component;
import ru.otus.library.dto.Comment;
import ru.otus.library.model.CommentEntity;

@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment fromEntity(CommentEntity entity) {
        return Comment.builder()
                .id(entity.getId())
                .text(entity.getText())
                .bookId(entity.getBookId())
                .build();
    }

    @Override
    public CommentEntity toEntity(Comment comment) {
        return CommentEntity.builder()
                .id(comment.getId())
                .text(comment.getText())
                .bookId(comment.getBookId())
                .build();
    }
}
