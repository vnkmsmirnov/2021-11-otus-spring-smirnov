package ru.otus.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.dto.Author;
import ru.otus.library.dto.Book;
import ru.otus.library.dto.Genre;
import ru.otus.library.service.*;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {

    private final BookService bookService;
    private final MongoSequenceService sequenceService;

    @ShellMethod(value = "Get book -> id", key = "b")
    public Book get(@ShellOption String id) {
        return bookService.get(id);
    }

    @ShellMethod(value = "Get all book", key = "b -a")
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @ShellMethod(value = "Save book -> title, pages, genreName, authorName", key = "b -s")
    public String save(@ShellOption String title,
                       @ShellOption Integer pages,
                       @ShellOption String genreName,
                       @ShellOption String authorName) {
        var book = Book.builder()
                .id(sequenceService.getSequence(SequencePrefix.BOOK))
                .title(title)
                .pages(pages)
                .genre(Genre.builder()
                        .id(sequenceService.getSequence(SequencePrefix.GENRE))
                        .name(genreName)
                        .build())
                .author(Author.builder()
                        .id(sequenceService.getSequence(SequencePrefix.AUTHOR))
                        .name(authorName)
                        .build())
                .build();
        var result = bookService.save(book);
        return String.format("The book saved ----> %s with id = %s", book.toString(), result.getId());
    }

    @ShellMethod(value = "Update book -> id, title, pages, genreId, genreName, authorId, authorName", key = "b -u")
    public String update(@ShellOption String id,
                         @ShellOption String title,
                         @ShellOption Integer pages,
                         @ShellOption String genreId,
                         @ShellOption String genreName,
                         @ShellOption String authorId,
                         @ShellOption String authorName) {
        var book = Book.builder()
                .id(id)
                .title(title)
                .pages(pages)
                .genre(Genre.builder()
                        .id(genreId)
                        .name(genreName)
                        .build())
                .author(Author.builder()
                        .id(authorId)
                        .name(authorName)
                        .build())
                .build();
        var result = bookService.save(book);
        return String.format("The book update ----> %s with id = %s", book.toString(), result.getId());
    }

    @ShellMethod(value = "Add comment book -> bookId, comment", key = "b -c")
    public String addComment(@ShellOption String bookId,
                             @ShellOption String comment) {
        var result = bookService.addComment(bookId, comment);
        return String.format("The comment added result is ----> %s", result);
    }

    @ShellMethod(value = "Delete book -> id", key = "b -d")
    public String delete(String id) {
        bookService.delete(id);
        return String.format("Book with %s deleted", id);
    }
}
