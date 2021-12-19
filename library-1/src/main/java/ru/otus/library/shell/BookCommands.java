package ru.otus.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.dto.Author;
import ru.otus.library.dto.Book;
import ru.otus.library.dto.Genre;
import ru.otus.library.service.BookService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {
    private final BookService bookService;

    @ShellMethod(value = "Get book -> id", key = "b")
    public Book getBook(@ShellOption Long id) {
        return bookService.get(id);
    }

    @ShellMethod(value = "Get all book", key = "b -a")
    public List<Book> getAllBook() {
        return bookService.getAll();
    }

    @ShellMethod(value = "Save book -> id, title, pages, genreId, genreName, authorId, authorFirstName, authorLastName", key = "b -s")
    public String saveBook(@ShellOption Long id,
                           @ShellOption String title,
                           @ShellOption Integer pages,
                           @ShellOption Long genreId,
                           @ShellOption String genreName,
                           @ShellOption Long authorId,
                           @ShellOption String authorFirstName,
                           @ShellOption String authorLastName) {
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
                        .firstName(authorFirstName)
                        .lastName(authorLastName)
                        .build())
                .build();
        var result = bookService.save(book);
        return String.format("The author saved ----> %s with id = %d", book.toString(), result);
    }

    @ShellMethod(value = "Delete book -> id", key = "b -d")
    public String deleteBook(Long id) {
        bookService.delete(id);
        return String.format("Book with %d deleted", id);
    }
}
