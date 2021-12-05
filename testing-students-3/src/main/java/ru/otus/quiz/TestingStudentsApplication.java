package ru.otus.quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.quiz.exception.QuizException;
import ru.otus.quiz.service.UserInteractionService;

@SpringBootApplication
public class TestingStudentsApplication {

	public static void main(String[] args) throws QuizException {
		var context = SpringApplication.run(TestingStudentsApplication.class, args);
		var interactionService = context.getBean(UserInteractionService.class);
		interactionService.interaction();
	}

}
