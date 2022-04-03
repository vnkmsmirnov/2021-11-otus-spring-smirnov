package ru.otus.library.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(
        name = "books-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("author"),
                @NamedAttributeNode("genre")
        }
)
@Entity
@Table(name = "BOOKS")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "PAGES", nullable = false)
    private Integer pages;

    @ManyToOne(targetEntity = AuthorEntity.class,  cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID")
    private AuthorEntity author;

    @ManyToOne(targetEntity = GenreEntity.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "GENRE_ID", referencedColumnName = "ID")
    private GenreEntity genre;

    @OneToMany(targetEntity = CommentEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID")
    private List<CommentEntity> comments;
}
