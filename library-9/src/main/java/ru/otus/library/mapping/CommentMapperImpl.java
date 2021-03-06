package ru.otus.library.mapping;

import org.springframework.stereotype.Component;
import ru.otus.library.dto.Comment;
import ru.otus.library.mongo.model.CommentDocument;

@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment fromEntity(CommentDocument entity) {
        return Comment.builder()
                .id(entity.getId())
                .text(entity.getText())
                .build();
    }

    @Override
    public CommentDocument toEntity(Comment comment) {
        return CommentDocument.builder()
                .id(comment.getId())
                .text(comment.getText())
                .build();
    }
}
