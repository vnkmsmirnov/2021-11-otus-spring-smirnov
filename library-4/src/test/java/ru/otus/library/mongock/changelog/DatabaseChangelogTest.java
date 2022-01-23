package ru.otus.library.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.library.model.AuthorDocument;
import ru.otus.library.model.BookDocument;
import ru.otus.library.model.CommentDocument;
import ru.otus.library.model.GenreDocument;
import ru.otus.library.repository.BookRepository;
import ru.otus.library.repository.CommentRepository;

import java.util.Collections;

@ChangeLog
public class DatabaseChangelogTest {

    @ChangeSet(order = "001", id = "dropDb", author = "smirnov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "initData", author = "smirnov", runAlways = true)
    public void initData(BookRepository bookRepository,
                         CommentRepository commentRepository) {
        var testBookId = "B_TEST_ID_1";
        var comments = Collections.singletonList(
                CommentDocument.builder()
                        .id("C_TEST_ID_1")
                        .text("TEST_COMMENT_1")
                        .bookId(testBookId)
                        .build());
        var books = Collections.singletonList(
                BookDocument.builder()
                        .id(testBookId)
                        .title("TEST_TITLE_1")
                        .pages(240)
                        .author(AuthorDocument.builder()
                                .name("TEST_AUTHOR_1")
                                .build())
                        .genre(GenreDocument.builder()
                                .name("TEST_GENRE_1")
                                .build())
                        .comments(comments)
                        .build()
        );

        bookRepository.insert(books);
        commentRepository.insert(comments);
    }
}
