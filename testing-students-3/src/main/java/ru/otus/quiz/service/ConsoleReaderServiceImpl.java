package ru.otus.quiz.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class ConsoleReaderServiceImpl implements ConsoleReaderService {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String read() {
        try {
            return reader.readLine().trim();
        } catch (IOException e) {
            System.out.println("Can't read line");
        }
        return StringUtils.EMPTY;
    }
}
