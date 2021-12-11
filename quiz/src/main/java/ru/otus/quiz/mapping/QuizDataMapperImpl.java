package ru.otus.quiz.mapping;

import org.springframework.stereotype.Component;
import ru.otus.quiz.dto.QuizData;

import java.util.Arrays;

@Component
public class QuizDataMapperImpl implements QuizDataMapper {

    @Override
    public QuizData map(String[] row) {
        return QuizData.builder()
                .question(row[0])
                .answers(Arrays.asList(row[1].split(",")))
                .correctAnswer(row[2])
                .build();
    }
}
