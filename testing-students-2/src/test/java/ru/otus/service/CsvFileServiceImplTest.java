package ru.otus.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.TestingStudentsApplication;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class CsvFileServiceImplTest {
    private static AnnotationConfigApplicationContext context;

    @BeforeAll
    public static void setUp() {
        context = new AnnotationConfigApplicationContext(TestingStudentsApplication.class);
    }

    @Test
    public void getInputStreamTest() {
        var resourceFileService = context.getBean(ResourceFileService.class);
        assertThat(resourceFileService.getInputStream()).isNotNull();
        var csvFileService = context.getBean(CsvFileService.class);
        var result = csvFileService.read(resourceFileService.getInputStream());
        assertThat(result.getQuizData())
                .hasSize(5);
        assertThat(result.getQuizData().get(0).getQuestion())
                .isEqualTo("What is the most important answer to all the questions of the Universe?");
        assertThat(result.getQuizData().get(0).getAnswers())
                .contains("42");
    }
}