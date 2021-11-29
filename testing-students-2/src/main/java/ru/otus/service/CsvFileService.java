package ru.otus.service;

import ru.otus.dto.Quiz;

import java.io.InputStream;

public interface CsvFileService {
    Quiz read(InputStream inputStream);
}
