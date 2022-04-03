package ru.otus.library.mongo.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "genre")
public class GenreDocument {

    private String id;

    private String name;
}
