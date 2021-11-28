package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dto.Quiz;
import ru.otus.props.AppProperties;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Service
public class QuizServiceImpl implements QuizService {
    private final CsvFileService csvFileService;
    private final ResourceFileService resourceFileService;
    private final AppProperties appProperties;
    private Quiz quiz;

    @PostConstruct
    public void init() {
        quiz = csvFileService.read(resourceFileService.getInputStream());
    }

    @Override
    public Quiz getQuiz() {
        return quiz;
    }

    @Override
    public int numberOfQuestions() {
        return quiz.getQuizData().size();
    }

    @Override
    public Integer correctAnswers() {
        return appProperties.getCorrectAnswers();
    }
}