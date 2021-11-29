package ru.otus.service;

import ru.otus.dto.Quiz;

public interface QuizService {
    Quiz getQuiz();
    int numberOfQuestions();
    Integer correctAnswers();
}