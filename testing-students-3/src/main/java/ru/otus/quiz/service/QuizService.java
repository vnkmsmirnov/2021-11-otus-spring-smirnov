package ru.otus.quiz.service;

import ru.otus.quiz.exception.QuizException;
import ru.otus.quiz.dto.Quiz;

public interface QuizService {
    Quiz getQuiz() throws QuizException;
    Integer numberOfQuestions() throws QuizException;
    Integer correctAnswers();
}