package ru.otus.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.library.dto.Genre;
import ru.otus.library.service.GenreService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/genre/{id}")
    public ResponseEntity<Genre> get(@PathVariable(value = "id") String id) {
        var result = genreService.get(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/genre")
    public ResponseEntity<List<Genre>> getAll() {
        var result = genreService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/genre")
    public ResponseEntity<Genre> save(@RequestBody Genre genre) {
        var result = genreService.save(genre);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/genre/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
        genreService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
