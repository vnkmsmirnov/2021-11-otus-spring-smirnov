package ru.otus.mapping;

import org.springframework.stereotype.Component;
import ru.otus.dto.QuizData;

import java.util.Arrays;

@Component
public class QuizDataMapper {

    public QuizData map(String[] row) {
        return QuizData.builder()
                .question(row[0])
                .answers(Arrays.asList(row[1].split(",")))
                .correctAnswer(row[2])
                .build();
    }
}
