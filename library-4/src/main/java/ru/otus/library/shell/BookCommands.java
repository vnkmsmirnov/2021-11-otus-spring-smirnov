package ru.otus.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.dto.Author;
import ru.otus.library.dto.Book;
import ru.otus.library.dto.Genre;
import ru.otus.library.service.BookService;
import ru.otus.library.service.MongoSequenceService;
import ru.otus.library.service.SequencePrefix;

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

    @ShellMethod(value = "Save book -> title, pages, genreName, authorFirstName, authorLastName", key = "b -s")
    public String save(@ShellOption String title,
                       @ShellOption Integer pages,
                       @ShellOption String genreName,
                       @ShellOption String authorName) {
        var book = Book.builder()
                .id(sequenceService.getSequence(SequencePrefix.BOOK))
                .title(title)
                .pages(pages)
                .genre(Genre.builder()
                        .name(genreName)
                        .build())
                .author(Author.builder()
                        .name(authorName)
                        .build())
                .build();
        var result = bookService.save(book);
        return String.format("The book saved ----> %s with id = %s", book.toString(), result);
    }

    @ShellMethod(value = "Update book -> id, title, pages, genreName, authorFirstName, authorLastName", key = "b -u")
    public String update(@ShellOption String id,
                         @ShellOption String title,
                         @ShellOption Integer pages,
                         @ShellOption String genreName,
                         @ShellOption String authorName) {
        var book = Book.builder()
                .id(id)
                .title(title)
                .pages(pages)
                .genre(Genre.builder()
                        .name(genreName)
                        .build())
                .author(Author.builder()
                        .name(authorName)
                        .build())
                .build();
        var result = bookService.save(book);
        return String.format("The book update ----> %s with id = %s", book.toString(), result);
    }

    @ShellMethod(value = "Delete book -> id", key = "b -d")
    public String delete(String id) {
        bookService.delete(id);
        return String.format("Book with %s deleted", id);
    }
}
