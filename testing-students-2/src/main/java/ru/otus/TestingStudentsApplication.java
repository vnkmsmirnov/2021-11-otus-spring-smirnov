package ru.otus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.service.UserInteractionService;

@Configuration
@ComponentScan
public class TestingStudentsApplication {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(TestingStudentsApplication.class);
        var interactionService = context.getBean(UserInteractionService.class);
        interactionService.interaction();
    }
}