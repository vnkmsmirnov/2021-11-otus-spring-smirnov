package ru.otus.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.dto.Author;
import ru.otus.library.service.AuthorService;
import ru.otus.library.service.MongoSequenceService;
import ru.otus.library.service.SequencePrefix;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class AuthorCommands {

    private final MongoSequenceService sequenceService;
    private final AuthorService authorService;

    @ShellMethod(value = "Get author -> id", key = "a")
    public Author get(@ShellOption String id) {
        return authorService.get(id);
    }

    @ShellMethod(value = "Get all author", key = "a -a")
    public List<Author> getAll() {
        return authorService.getAll();
    }

    @ShellMethod(value = "Save author -> name", key = "a -s")
    public String save(@ShellOption String name) {
        var author = Author.builder()
                .id(sequenceService.getSequence(SequencePrefix.AUTHOR))
                .name(name)
                .build();
        var result = authorService.save(author);
        return String.format("The author saved ----> %s with id = %s", author.getName(), result.getId());
    }

    @ShellMethod(value = "Update author -> id, name", key = "a -u")
    public String update(@ShellOption String id,
                         @ShellOption String name) {
        var author = Author.builder()
                .id(id)
                .name(name)
                .build();
        var result = authorService.save(author);
        return String.format("The author update ----> %s with id = %s", author.getName(), result.getId());
    }

    @ShellMethod(value = "Delete author -> id", key = "a -d")
    public String delete(String id) {
        authorService.delete(id);
        return String.format("Author with %s deleted", id);
    }
}