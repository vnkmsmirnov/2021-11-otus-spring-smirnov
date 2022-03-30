package ru.otus.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.library.dto.Author;
import ru.otus.library.service.AuthorService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/author/{id}")
    public ResponseEntity<Author> get(@PathVariable(value = "id") Long id) {
        var result = authorService.get(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/author")
    public ResponseEntity<List<Author>> getAll() {
        var result = authorService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/author")
    public ResponseEntity<Author> save(@RequestBody Author author) {
        var result = authorService.save(author);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        authorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
