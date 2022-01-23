package ru.otus.library.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "comment")
public class CommentDocument {

    @Id
    private String id;

    private String text;

    private String bookId;
}
