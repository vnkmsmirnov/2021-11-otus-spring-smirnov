package ru.otus.quiz.service;

public interface MessageService {
    String get(String key);

    String get(String key, String... params);
}
