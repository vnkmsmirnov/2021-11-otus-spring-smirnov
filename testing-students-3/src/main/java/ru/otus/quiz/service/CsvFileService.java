package ru.otus.quiz.service;

import ru.otus.quiz.dto.Quiz;

import java.io.InputStream;

public interface CsvFileService {
    Quiz read(InputStream inputStream);
}
