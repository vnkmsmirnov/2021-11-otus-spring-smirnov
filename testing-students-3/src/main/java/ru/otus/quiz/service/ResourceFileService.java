package ru.otus.quiz.service;

import ru.otus.quiz.exception.QuizException;

import java.io.InputStream;

public interface ResourceFileService {
    InputStream getInputStream() throws QuizException;
}