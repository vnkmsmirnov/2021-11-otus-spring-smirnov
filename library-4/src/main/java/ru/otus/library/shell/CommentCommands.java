package ru.otus.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.dto.Comment;
import ru.otus.library.service.CommentService;
import ru.otus.library.service.MongoSequenceService;
import ru.otus.library.service.SequencePrefix;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class CommentCommands {

    private final MongoSequenceService sequenceService;
    private final CommentService commentService;

    @ShellMethod(value = "Get comment -> id", key = "c")
    public Comment get(@ShellOption String id) {
        return commentService.get(id);
    }

    @ShellMethod(value = "Get all comments", key = "c -a")
    public List<Comment> getAll() {
        return commentService.getAll();
    }

    @ShellMethod(value = "Get comments by book id -> id", key = "c -b")
    public List<Comment> getByBookId(@ShellOption String id) {
        return commentService.getByBookId(id);
    }

    @ShellMethod(value = "Add comment -> text, bookId", key = "c -s")
    public String update(@ShellOption String text,
                         @ShellOption String bookId) {
        var comment = Comment.builder()
                .id(sequenceService.getSequence(SequencePrefix.COMMENT))
                .text(text)
                .bookId(bookId)
                .build();
        var result = commentService.save(comment);
        return String.format("The comment added ----> %s with id = %s", comment.getText(), result);
    }

    @ShellMethod(value = "Delete comment -> id", key = "c -d")
    public String delete(String id) {
        commentService.delete(id);
        return String.format("Comment with %s deleted", id);
    }
}
