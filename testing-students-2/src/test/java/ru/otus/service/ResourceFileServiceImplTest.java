package ru.otus.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.TestingStudentsApplication;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ResourceFileServiceImplTest {
    private static AnnotationConfigApplicationContext context;

    @BeforeAll
    public static void setUp() {
        context = new AnnotationConfigApplicationContext(TestingStudentsApplication.class);
    }

    @Test
    public void getInputStreamTest() {
        var resourceFileService = context.getBean(ResourceFileService.class);
        assertThat(resourceFileService.getInputStream()).isNotNull();
    }
}