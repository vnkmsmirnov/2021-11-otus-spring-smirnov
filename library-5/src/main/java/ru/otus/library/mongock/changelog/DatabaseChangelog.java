package ru.otus.library.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.library.model.AuthorDocument;
import ru.otus.library.model.BookDocument;
import ru.otus.library.model.GenreDocument;
import ru.otus.library.repository.AuthorRepository;
import ru.otus.library.repository.BookRepository;
import ru.otus.library.repository.GenreRepository;
import ru.otus.library.service.SequencePrefix;

import java.util.List;
import java.util.UUID;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "smirnov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "initData", author = "smirnov", runAlways = true)
    public void initData(BookRepository bookRepository,
                         AuthorRepository authorRepository,
                         GenreRepository genreRepository) {

        var detectiveGenre = genreRepository.insert(GenreDocument.builder()
                .id(SequencePrefix.GENRE.getName().concat(UUID.randomUUID().toString()))
                .name("Детективы")
                .build());
        var fictionGenre = genreRepository.insert(GenreDocument.builder()
                .id(SequencePrefix.GENRE.getName().concat(UUID.randomUUID().toString()))
                .name("Фантастика")
                .build());
        var fantasyGenre = genreRepository.insert(GenreDocument.builder()
                .id(SequencePrefix.GENRE.getName().concat(UUID.randomUUID().toString()))
                .name("Фэнтези")
                .build());
        var adventureGenre = genreRepository.insert(GenreDocument.builder()
                .id(SequencePrefix.GENRE.getName().concat(UUID.randomUUID().toString()))
                .name("Приключения")
                .build());
        var lightFictionGenre = genreRepository.insert(GenreDocument.builder()
                .id(SequencePrefix.GENRE.getName().concat(UUID.randomUUID().toString()))
                .name("Легкая проза")
                .build());
        var classicGenre = genreRepository.insert(GenreDocument.builder()
                .id(SequencePrefix.GENRE.getName().concat(UUID.randomUUID().toString()))
                .name("Классика жанра")
                .build());

        var books = List.of(
                BookDocument.builder()
                        .id(SequencePrefix.BOOK.getName().concat(UUID.randomUUID().toString()))
                        .title("Швейцарский счет")
                        .pages(240)
                        .author(authorRepository.insert(AuthorDocument.builder()
                                .id(SequencePrefix.AUTHOR.getName().concat(UUID.randomUUID().toString()))
                                .name("Елена Саулите")
                                .build()))
                        .genre(detectiveGenre)
                        .build(),
                BookDocument.builder()
                        .id(SequencePrefix.BOOK.getName().concat(UUID.randomUUID().toString()))
                        .title("Как ты умрешь")
                        .pages(310)
                        .author(authorRepository.insert(AuthorDocument.builder()
                                .id(SequencePrefix.AUTHOR.getName().concat(UUID.randomUUID().toString()))
                                .name("Майк Омер")
                                .build()))
                        .genre(detectiveGenre)
                        .build(),
                BookDocument.builder()
                        .id(SequencePrefix.BOOK.getName().concat(UUID.randomUUID().toString()))
                        .title("Три дня Индиго")
                        .pages(350)
                        .author(authorRepository.insert(AuthorDocument.builder()
                                .id(SequencePrefix.AUTHOR.getName().concat(UUID.randomUUID().toString()))
                                .name("Сергей Лукьяненко")
                                .build()))
                        .genre(fictionGenre)
                        .build(),
                BookDocument.builder()
                        .id(SequencePrefix.BOOK.getName().concat(UUID.randomUUID().toString()))
                        .title("Позже")
                        .pages(240)
                        .author(authorRepository.insert(AuthorDocument.builder()
                                .id(SequencePrefix.AUTHOR.getName().concat(UUID.randomUUID().toString()))
                                .name("Стивен Кинг")
                                .build()))
                        .genre(fantasyGenre)
                        .build(),
                BookDocument.builder()
                        .id(SequencePrefix.BOOK.getName().concat(UUID.randomUUID().toString()))
                        .title("Граф Монте-Кристо")
                        .pages(2292)
                        .author(authorRepository.insert(AuthorDocument.builder()
                                .id(SequencePrefix.AUTHOR.getName().concat(UUID.randomUUID().toString()))
                                .name("Александр Дюма")
                                .build()))
                        .genre(adventureGenre)
                        .build(),
                BookDocument.builder()
                        .id(SequencePrefix.BOOK.getName().concat(UUID.randomUUID().toString()))
                        .title("Дорогая Дуся")
                        .pages(190)
                        .author(authorRepository.insert(AuthorDocument.builder()
                                .id(SequencePrefix.AUTHOR.getName().concat(UUID.randomUUID().toString()))
                                .name("Елена Колина")
                                .build()))
                        .genre(lightFictionGenre)
                        .build(),
                BookDocument.builder()
                        .id(SequencePrefix.BOOK.getName().concat(UUID.randomUUID().toString()))
                        .title("Потерянное наследство тамплиера")
                        .pages(230)
                        .author(authorRepository.insert(AuthorDocument.builder()
                                .id(SequencePrefix.AUTHOR.getName().concat(UUID.randomUUID().toString()))
                                .name("Юлия Ефимова")
                                .build()))
                        .genre(classicGenre)
                        .build()
        );

        bookRepository.insert(books);
    }
}
