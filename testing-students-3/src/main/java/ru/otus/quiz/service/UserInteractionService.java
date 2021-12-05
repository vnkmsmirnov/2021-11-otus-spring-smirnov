package ru.otus.quiz.service;

import ru.otus.quiz.exception.QuizException;

public interface UserInteractionService {
    void interaction() throws QuizException;
}
