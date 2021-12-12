package ru.otus.quiz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.quiz.dto.Quiz;
import ru.otus.quiz.properties.AppProperties;

@RequiredArgsConstructor
@Service
public class QuizServiceImpl implements QuizService {
    private final CsvResourceFileService resourceFileService;
    private final AppProperties appProperties;
    private Quiz quiz;

    @Override
    public Quiz getQuiz() {
        if (quiz == null) {
            quiz = resourceFileService.read(appProperties.getPath());
        }
        return quiz;
    }

    @Override
    public Integer numberOfQuestions() {
        return getQuiz().getQuizData().size();
    }

    @Override
    public Integer correctAnswers() {
        return appProperties.getCorrectAnswers();
    }
}