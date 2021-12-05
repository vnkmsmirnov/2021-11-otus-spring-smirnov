package ru.otus.quiz.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.quiz.exception.QuizException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
class ResourceFileServiceImplTest {
    @Autowired
    private ResourceFileService resourceFileService;

    @Test
    public void getInputStreamTest() throws QuizException {
        assertThat(resourceFileService.getInputStream()).isNotNull();
    }
}