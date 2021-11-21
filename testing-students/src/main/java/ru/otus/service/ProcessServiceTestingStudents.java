package ru.otus.service;

import lombok.RequiredArgsConstructor;
import ru.otus.dto.Quiz;

@RequiredArgsConstructor
public class ProcessServiceTestingStudents implements ProcessService {
    private final FileService fileService;
    private final String path;
    private Quiz quiz;

    public void init() {
        quiz = fileService.read(path);
    }

    @Override
    public void showQuiz() {
        System.out.println(quiz);
    }
}