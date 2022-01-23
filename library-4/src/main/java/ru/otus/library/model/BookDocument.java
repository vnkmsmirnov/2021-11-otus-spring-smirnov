package ru.otus.library.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

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

    private AuthorDocument author;

    private GenreDocument genre;

    @ReadOnlyProperty
    @DocumentReference(lookup = "{'bookId':?#{#self._id} }", lazy = true)
    private List<CommentDocument> comments;
}
