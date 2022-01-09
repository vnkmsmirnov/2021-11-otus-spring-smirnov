package ru.otus.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.dto.Comment;
import ru.otus.library.service.CommentService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class CommentCommands {

    private final CommentService commentService;

    @ShellMethod(value = "Get comment -> id", key = "c")
    public Comment get(@ShellOption Long id) {
        return commentService.get(id);
    }

    @ShellMethod(value = "Get all comments", key = "c -a")
    public List<Comment> getAll() {
        return commentService.getAll();
    }

    @ShellMethod(value = "Get comments by book id -> id", key = "c -b")
    public List<Comment> getByBookId(@ShellOption Long id) {
        return commentService.getByBookId(id);
    }

    @ShellMethod(value = "Add comment -> text, bookId", key = "c -s")
    public String update(@ShellOption String text,
                         @ShellOption Long bookId) {
        var comment = Comment.builder()
                .text(text)
                .bookId(bookId)
                .build();
        var result = commentService.save(comment);
        return String.format("The comment added ----> %s with id = %d", comment.getText(), result);
    }

    @ShellMethod(value = "Delete comment -> id", key = "c -d")
    public String delete(Long id) {
        commentService.delete(id);
        return String.format("Comment with %d deleted", id);
    }
}
