package ru.otus.quiz.service;

import ru.otus.quiz.dto.Quiz;

public interface QuizService {
    Quiz getQuiz();

    Integer numberOfQuestions();

    Integer correctAnswers();
}