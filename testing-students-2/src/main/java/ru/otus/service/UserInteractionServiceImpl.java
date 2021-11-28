package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dto.QuizData;
import ru.otus.dto.UserInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RequiredArgsConstructor
@Service
public class UserInteractionServiceImpl implements UserInteractionService {
    private final QuizService processService;
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void interaction() {
        var userInfo = greeting();
        int correctAnswersCount = 0;
        for(QuizData quizData : processService.getQuiz().getQuizData()) {
            System.out.println(quizData.getQuestion() +
                    "\n ================================ \n" +
                    "answer options ============> " + quizData.getAnswers());
            try {
                System.out.print("enter your answer ---> ");
                var line = reader.readLine();
                System.out.println();
                if (quizData.getCorrectAnswer().equalsIgnoreCase(line)) {
                    correctAnswersCount++;
                }
            } catch (IOException e) {
                System.out.println("Something went wrong!");
            }
        }
        if (correctAnswersCount >= processService.correctAnswers()) {
            System.out.println(userInfo.getFirstName() + " congratulations, you won!");
        } else {
            System.out.println(userInfo.getFirstName() + " unfortunately you have not passed the quiz :(");
        }
    }

    private UserInfo greeting() {
        var userInfo = UserInfo.builder();
        System.out.println("Hello!");
        System.out.print("enter your last name  ----> ");
        try {
            userInfo.lastName(reader.readLine().trim());
            System.out.println();
            System.out.print("enter your first name ----> ");
            userInfo.firstName(reader.readLine().trim());
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
        var result = userInfo.build();
        System.out.println("\n" +
                result.getFirstName() + " " + result.getLastName() +
                " welcome to our quiz! \nThere will be " +
                processService.numberOfQuestions() +
                " questions in total, for pass you need to answer " +
                processService.correctAnswers() +
                " correctly. \nGood luck!\n");

        return result;
    }
}
