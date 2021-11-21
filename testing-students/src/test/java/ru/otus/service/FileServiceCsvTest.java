package ru.otus.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class FileServiceCsvTest {
    private static ClassPathXmlApplicationContext context;

    @BeforeAll
    public static void setUp() {
        context = new ClassPathXmlApplicationContext("/test-spring-context.xml");
    }

    @Test
    public void readTest() {
        var fileService = context.getBean(FileServiceCsv.class);
        var result = fileService.read("data/test-question-and-answers.csv");
        assertThat(result.getQuestionAndAnswers())
                .hasSize(5);
        assertThat(result.getQuestionAndAnswers().get(0).getQuestion())
                .isEqualTo("What is the most important answer to all the questions of the Universe?");
        assertThat(result.getQuestionAndAnswers().get(0).getAnswers())
                .contains("42");
    }
}