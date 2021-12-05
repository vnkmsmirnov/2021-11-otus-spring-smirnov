package ru.otus.quiz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.quiz.exception.QuizException;
import ru.otus.quiz.dto.QuizData;
import ru.otus.quiz.dto.UserInfo;

@RequiredArgsConstructor
@Service
public class UserInteractionServiceImpl implements UserInteractionService {
    private final QuizService processService;
    private final ConsoleReaderService readerService;
    private final MessageService messageService;

    @Override
    public void interaction() throws QuizException {
        var userInfo = greeting();
        int correctAnswersCount = 0;
        for(QuizData quizData : processService.getQuiz().getQuizData()) {
            System.out.println(messageService.get("question", quizData.getQuestion(), quizData.getAnswers().toString()));
            System.out.print(messageService.get("answer"));
            var line = readerService.read();
            System.out.println();
            if (quizData.getCorrectAnswer().equalsIgnoreCase(line)) {
                correctAnswersCount++;
            }
        }
        if (correctAnswersCount >= processService.correctAnswers()) {
            System.out.println(messageService.get("won", userInfo.getFirstName()));
        } else {
            System.out.println(messageService.get("loss", userInfo.getFirstName()));
        }
    }

    private UserInfo greeting() throws QuizException {
        var userInfo = UserInfo.builder();
        System.out.println(messageService.get("hello"));
        System.out.print(messageService.get("last-name"));
        userInfo.lastName(readerService.read());
        System.out.println();
        System.out.print(messageService.get("first-name"));
        userInfo.firstName(readerService.read());
        var result = userInfo.build();
        System.out.println(messageService.get("greeting", result.getFirstName(), result.getLastName(),
                String.valueOf(processService.numberOfQuestions()), String.valueOf(processService.correctAnswers())));
        return result;
    }
}
