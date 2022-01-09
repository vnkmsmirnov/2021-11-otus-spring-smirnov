package ru.otus.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.dto.Genre;
import ru.otus.library.service.GenreService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class GenreCommands {

    private final GenreService genreService;

    @ShellMethod(value = "Get genre -> id", key = "g")
    public Genre get(@ShellOption Long id) {
        return genreService.get(id);
    }

    @ShellMethod(value = "Get all genres", key = "g -a")
    public List<Genre> getAll() {
        return genreService.getAll();
    }

    @ShellMethod(value = "Save genre -> name", key = "g -s")
    public String save(@ShellOption String name) {
        var genre = Genre.builder()
                .name(name)
                .build();
        var result = genreService.save(genre);
        return String.format("The genre saved ----> %s with id = %d", genre.getName(), result);
    }

    @ShellMethod(value = "Update genre -> id, name", key = "g -u")
    public String update(@ShellOption Long id,
                         @ShellOption String name) {
        var genre = Genre.builder()
                .id(id)
                .name(name)
                .build();
        var result = genreService.save(genre);
        return String.format("The genre update ----> %s with id = %d", genre.getName(), result);
    }
}
