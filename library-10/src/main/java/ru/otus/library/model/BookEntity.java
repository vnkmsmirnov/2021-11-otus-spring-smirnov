package ru.otus.library.model;

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

    @Column(name = "AUTHOR_ID")
    private Long authorId;

    @Column(name = "GENRE_ID")
    private Long genreId;

    @ManyToOne(targetEntity = AuthorEntity.class,  cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private AuthorEntity author;

    @ManyToOne(targetEntity = GenreEntity.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "GENRE_ID",referencedColumnName = "ID", insertable = false, updatable = false)
    private GenreEntity genre;

    @OneToMany(targetEntity = CommentEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private List<CommentEntity> comments;
}
