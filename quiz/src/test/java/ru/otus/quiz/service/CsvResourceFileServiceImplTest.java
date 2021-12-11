package ru.otus.quiz.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.quiz.properties.AppProperties;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
class CsvResourceFileServiceImplTest {
    @Autowired
    private CsvResourceFileService resourceFileService;
    @Autowired
    private AppProperties appProperties;

    @Test
    public void readCsvFileTest() {
        var result = resourceFileService.read(appProperties.getPath());
        assertThat(result.getQuizData())
                .hasSize(5);
        assertThat(result.getQuizData().get(0).getQuestion())
                .isEqualTo("What is the most important answer to all the questions of the Universe?");
        assertThat(result.getQuizData().get(0).getAnswers())
                .contains("42");
    }
}