package ru.otus.service;

import ru.otus.dto.Quiz;

public interface FileService {
    Quiz read(String path);
}