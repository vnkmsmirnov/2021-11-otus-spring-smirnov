package ru.otus.quiz.mapping;

import ru.otus.quiz.dto.QuizData;

public interface QuizDataMapper {
    QuizData map(String[] row);
}
