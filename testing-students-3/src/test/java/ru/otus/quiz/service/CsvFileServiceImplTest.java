package ru.otus.quiz.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.quiz.exception.QuizException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
class CsvFileServiceImplTest {
    @Autowired
    private ResourceFileService resourceFileService;
    @Autowired
    private CsvFileService csvFileService;

    @Test
    public void getInputStreamTest() throws QuizException {
        assertThat(resourceFileService.getInputStream()).isNotNull();
        var result = csvFileService.read(resourceFileService.getInputStream());
        assertThat(result.getQuizData())
                .hasSize(5);
        assertThat(result.getQuizData().get(0).getQuestion())
                .isEqualTo("What is the most important answer to all the questions of the Universe?");
        assertThat(result.getQuizData().get(0).getAnswers())
                .contains("42");
    }
}