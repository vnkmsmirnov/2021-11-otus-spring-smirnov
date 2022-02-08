package ru.otus.library.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "book")
public class BookDocument {

    @Id
    private String id;

    private String title;

    private Integer pages;

    @DBRef
    private AuthorDocument author;

    @DBRef
    private GenreDocument genre;

    private List<CommentDocument> comments;
}