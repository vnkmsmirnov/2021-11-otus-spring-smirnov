package ru.otus.library.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.library.mongo.model.AuthorDocument;
import ru.otus.library.mongo.model.BookDocument;
import ru.otus.library.mongo.model.CommentDocument;
import ru.otus.library.mongo.model.GenreDocument;
import ru.otus.library.mongo.repository.AuthorMongoRepository;
import ru.otus.library.mongo.repository.BookMongoRepository;
import ru.otus.library.mongo.repository.GenreMongoRepository;

import java.util.Collections;

@ChangeLog
public class DatabaseChangelogTest {

    @ChangeSet(order = "001", id = "dropDb", author = "smirnov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "initData", author = "smirnov", runAlways = true)
    public void initData(BookMongoRepository bookRepository,
                         AuthorMongoRepository authorRepository,
                         GenreMongoRepository genreRepository) {
        var testBookId = "B_TEST_ID_1";
        var comments = Collections.singletonList(
                CommentDocument.builder()
                        .id("C_TEST_ID_1")
                        .text("TEST_COMMENT_1")
                        .build());
        var books = Collections.singletonList(
                BookDocument.builder()
                        .id(testBookId)
                        .title("TEST_TITLE_1")
                        .pages(240)
                        .author(authorRepository.insert(AuthorDocument.builder()
                                .id("A_TEST_ID_1")
                                .name("TEST_AUTHOR_1")
                                .build()))
                        .genre(genreRepository.insert(GenreDocument.builder()
                                .id("G_TEST_ID_1")
                                .name("TEST_GENRE_1")
                                .build()))
                        .comments(comments)
                        .build()
        );

        bookRepository.insert(books);
    }
}
