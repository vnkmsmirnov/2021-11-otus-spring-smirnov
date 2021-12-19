package ru.otus.library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity {
    private Long id;
    private String title;
    private Integer pages;
    private GenreEntity genre;
    private AuthorEntity author;
}
