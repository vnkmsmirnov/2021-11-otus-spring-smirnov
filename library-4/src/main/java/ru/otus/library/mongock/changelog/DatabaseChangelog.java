package ru.otus.library.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.library.model.AuthorDocument;
import ru.otus.library.model.BookDocument;
import ru.otus.library.model.GenreDocument;
import ru.otus.library.repository.BookRepository;
import ru.otus.library.service.SequencePrefix;

import java.util.List;
import java.util.UUID;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "smirnov")
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "initData", author = "smirnov")
    public void initData(BookRepository repository) {
        var books = List.of(
                BookDocument.builder()
                        .id(SequencePrefix.BOOK.getName().concat(UUID.randomUUID().toString()))
                        .title("Швейцарский счет")
                        .pages(240)
                        .author(AuthorDocument.builder()
                                .name("Елена Саулите")
                                .build())
                        .genre(GenreDocument.builder()
                                .name("Детективы")
                                .build())
                        .build(),
                BookDocument.builder()
                        .id(SequencePrefix.BOOK.getName().concat(UUID.randomUUID().toString()))
                        .title("Как ты умрешь")
                        .pages(310)
                        .author(AuthorDocument.builder()
                                .name("Майк Омер")
                                .build())
                        .genre(GenreDocument.builder()
                                .name("Детективы")
                                .build())
                        .build(),
                BookDocument.builder()
                        .id(SequencePrefix.BOOK.getName().concat(UUID.randomUUID().toString()))
                        .title("Три дня Индиго")
                        .pages(350)
                        .author(AuthorDocument.builder()
                                .name("Сергей Лукьяненко")
                                .build())
                        .genre(GenreDocument.builder()
                                .name("Фантастика")
                                .build())
                        .build(),
                BookDocument.builder()
                        .id(SequencePrefix.BOOK.getName().concat(UUID.randomUUID().toString()))
                        .title("Позже")
                        .pages(240)
                        .author(AuthorDocument.builder()
                                .name("Стивен Кинг")
                                .build())
                        .genre(GenreDocument.builder()
                                .name("Фэнтези")
                                .build())
                        .build(),
                BookDocument.builder()
                        .id(SequencePrefix.BOOK.getName().concat(UUID.randomUUID().toString()))
                        .title("Граф Монте-Кристо")
                        .pages(2292)
                        .author(AuthorDocument.builder()
                                .name("Александр Дюма")
                                .build())
                        .genre(GenreDocument.builder()
                                .name("Приключения")
                                .build())
                        .build(),
                BookDocument.builder()
                        .id(SequencePrefix.BOOK.getName().concat(UUID.randomUUID().toString()))
                        .title("Дорогая Дуся")
                        .pages(190)
                        .author(AuthorDocument.builder()
                                .name("Елена Колина")
                                .build())
                        .genre(GenreDocument.builder()
                                .name("Легкая проза")
                                .build())
                        .build(),
                BookDocument.builder()
                        .id(SequencePrefix.BOOK.getName().concat(UUID.randomUUID().toString()))
                        .title("Потерянное наследство тамплиера")
                        .pages(230)
                        .author(AuthorDocument.builder()
                                .name("Юлия Ефимова")
                                .build())
                        .genre(GenreDocument.builder()
                                .name("Классика жанра")
                                .build())
                        .build()
        );

        repository.insert(books);
    }
}
