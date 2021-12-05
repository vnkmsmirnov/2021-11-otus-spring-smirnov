package ru.otus.quiz.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
class AppPropertiesTest {
    @Autowired
    private AppProperties appProperties;

    @Test
    public void pathTest() {
        assertThat(appProperties.getPath()).isNotNull();
        assertThat(appProperties.getPath()).isEqualTo("data/test-question-and-answers.csv");
        assertThat(appProperties.getCorrectAnswers()).isNotNull();
        assertThat(appProperties.getCorrectAnswers()).isEqualTo(4);
    }
}