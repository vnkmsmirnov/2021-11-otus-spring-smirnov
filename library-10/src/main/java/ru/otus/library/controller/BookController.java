package ru.otus.library.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.library.dto.Book;
import ru.otus.library.dto.Comment;
import ru.otus.library.service.BookService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> get(@PathVariable(value = "id") Long id) {
        log.info("Get book by id=[{}]", id);
        var result = bookService.get(id);
        log.info("Result >>> {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/book")
    public ResponseEntity<List<Book>> getAll() {
        log.info("Get all books");
        var result = bookService.getAll();
        log.info("Find books >>> {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<Book> save(@RequestBody Book book) {
        log.info("Save book >>> {}", book);
        var result = bookService.save(book);
        log.info("Saved book >>> {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/book/{id}/comment")
    public ResponseEntity<Book> addComment(@PathVariable(value = "id") Long id,
                                           @RequestBody Comment comment) {
        log.info("Add comment >>> {}", comment);
        var result = bookService.addComment(id, comment.getText());
        log.info("Result >>> {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        log.info("Delete book by id >>> {}", id);
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
