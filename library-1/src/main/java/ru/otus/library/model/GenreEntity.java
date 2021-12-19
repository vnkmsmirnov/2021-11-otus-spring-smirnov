package ru.otus.library.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenreEntity {
    private Long id;
    private String name;
}
