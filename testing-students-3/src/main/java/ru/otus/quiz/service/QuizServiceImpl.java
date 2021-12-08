package ru.otus.quiz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.quiz.exception.QuizException;
import ru.otus.quiz.dto.Quiz;
import ru.otus.quiz.properties.AppProperties;

@RequiredArgsConstructor
@Service
public class QuizServiceImpl implements QuizService {
    private final CsvFileService csvFileService;
    private final ResourceFileService resourceFileService;
    private final AppProperties appProperties;
    private Quiz quiz;

    @Override
    public Quiz getQuiz() throws QuizException {
        if (quiz == null) {
            quiz = csvFileService.read(resourceFileService.getInputStream());
        }
        return quiz;
    }

    @Override
    public Integer numberOfQuestions() throws QuizException {
        return getQuiz().getQuizData().size();
    }

    @Override
    public Integer correctAnswers() {
        return appProperties.getCorrectAnswers();
    }
}