package ru.otus.library.mapping.migration;

import org.springframework.stereotype.Component;
import ru.otus.library.jpa.model.CommentEntity;
import ru.otus.library.mongo.model.CommentDocument;

import java.util.Optional;

@Component
public class CommentMigrationMapperImpl implements CommentMigrationMapper {

    @Override
    public CommentEntity map(CommentDocument document) {
        return CommentEntity.builder()
                .text(Optional.ofNullable(document)
                        .map(CommentDocument::getText)
                        .orElse(null))
                .build();
    }
}
