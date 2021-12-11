package ru.otus.quiz.service;

import ru.otus.quiz.dto.Quiz;

public interface CsvResourceFileService {
    Quiz read(String path);
}