package ru.otus.library.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "author")
public class AuthorDocument {

    private String id;

    private String name;
}
