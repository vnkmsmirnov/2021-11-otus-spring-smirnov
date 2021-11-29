package ru.otus.props;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.TestingStudentsApplication;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AppPropertiesTest {
    private static AnnotationConfigApplicationContext context;

    @BeforeAll
    public static void setUp() {
        context = new AnnotationConfigApplicationContext(TestingStudentsApplication.class);
    }

    @Test
    public void pathTest() {
        var appProperties = context.getBean(AppProperties.class);
        assertThat(appProperties.getPath()).isNotNull();
        assertThat(appProperties.getPath()).isEqualTo("data/question-and-answers.csv");
        assertThat(appProperties.getCorrectAnswers()).isNotNull();
        assertThat(appProperties.getCorrectAnswers()).isEqualTo(4);
    }
}