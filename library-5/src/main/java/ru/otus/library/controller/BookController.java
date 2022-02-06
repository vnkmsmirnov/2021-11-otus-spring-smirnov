package ru.otus.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.library.dto.Book;
import ru.otus.library.dto.Comment;
import ru.otus.library.service.BookService;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> get(@PathVariable(value = "id") String id) {
        var result = bookService.get(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/book")
    public ResponseEntity<List<Book>> getAll() {
        var result = bookService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<Book> save(@RequestBody Book book) {
        var result = bookService.save(book);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/book/{id}/comment")
    public ResponseEntity<Book> addComment(@PathVariable(value = "id") String id,
                                           @RequestBody Comment comment) {
        var result = bookService.addComment(id, comment.getText());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
