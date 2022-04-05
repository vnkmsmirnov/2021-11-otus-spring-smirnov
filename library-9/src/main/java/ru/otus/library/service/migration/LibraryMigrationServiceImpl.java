package ru.otus.library.service.migration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.jpa.model.BookEntity;
import ru.otus.library.jpa.repository.AuthorRepository;
import ru.otus.library.jpa.repository.GenreRepository;
import ru.otus.library.mapping.migration.BookMigrationMapper;
import ru.otus.library.mongo.model.BookDocument;

@RequiredArgsConstructor
@Service
public class LibraryMigrationServiceImpl implements LibraryMigrationService {

    private final BookMigrationMapper bookMigrationMapper;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public BookEntity prepareBook(BookDocument document) {
        var book = bookMigrationMapper.map(document);
        var author = authorRepository.findByFirstNameAndLastName(book.getAuthor().getFirstName(), book.getAuthor().getLastName());
        if (author == null) {
            author = authorRepository.save(book.getAuthor());
        }
        var genre = genreRepository.findByName(book.getGenre().getName());
        if (genre == null) {
            genre = genreRepository.save(book.getGenre());
        }

        book.setAuthor(author);
        book.setGenre(genre);
        return book;
    }
}
