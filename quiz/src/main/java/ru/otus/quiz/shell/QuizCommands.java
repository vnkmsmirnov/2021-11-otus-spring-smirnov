package ru.otus.quiz.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.quiz.service.UserInteractionService;

@ShellComponent
@RequiredArgsConstructor
public class QuizCommands {
    private final UserInteractionService userInteractionService;

    @ShellMethod(value = "Start quiz", key = {"s", "start"})
    public void start() {
        userInteractionService.interaction();
    }
}
