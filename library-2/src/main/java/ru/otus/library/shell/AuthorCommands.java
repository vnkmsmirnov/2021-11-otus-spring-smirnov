package ru.otus.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.dto.Author;
import ru.otus.library.service.AuthorService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class AuthorCommands {

    private final AuthorService authorService;

    @ShellMethod(value = "Get author -> id", key = "a")
    public Author get(@ShellOption Long id) {
        return authorService.get(id);
    }

    @ShellMethod(value = "Get all author", key = "a -a")
    public List<Author> getAll() {
        return authorService.getAll();
    }

    @ShellMethod(value = "Save author -> first-name, last-name", key = "a -s")
    public String save(@ShellOption String firstName,
                       @ShellOption String lastName) {
        var author = Author.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
        var result = authorService.save(author);
        return String.format("The author saved ----> %s %s with id = %d", author.getFirstName(), author.getLastName(), result);
    }

    @ShellMethod(value = "Update author -> id, first-name, last-name", key = "a -u")
    public String update(@ShellOption Long id,
                         @ShellOption String firstName,
                         @ShellOption String lastName) {
        var author = Author.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .build();
        var result = authorService.save(author);
        return String.format("The author update ----> %s %s with id = %d", author.getFirstName(), author.getLastName(), result);
    }
}
