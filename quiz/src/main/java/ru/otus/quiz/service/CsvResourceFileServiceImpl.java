package ru.otus.quiz.service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import ru.otus.quiz.dto.Quiz;
import ru.otus.quiz.dto.QuizData;
import ru.otus.quiz.mapping.QuizDataMapperImpl;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class CsvResourceFileServiceImpl implements CsvResourceFileService {
    private final QuizDataMapperImpl quizDataMapper;
    private final Locale locale = LocaleContextHolder.getLocale();

    @Override
    public Quiz read(String path) {
        var inputStream = getInputStream(path);
        if (inputStream == null) {
            return Quiz.builder().build();
        }
        var csvParser = new CSVParserBuilder()
                .withSeparator(';')
                .build();
        try (var streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             var reader = new CSVReaderBuilder(streamReader)
                     .withCSVParser(csvParser)
                     .withSkipLines(1)
                     .build()) {
            String[] nextLine;
            var result = new ArrayList<QuizData>();
            while ((nextLine = reader.readNext()) != null) {
                result.add(quizDataMapper.map(nextLine));
            }
            return Quiz.builder()
                    .quizData(result)
                    .build();
        } catch (IOException | CsvException e) {
            return Quiz.builder().build();
        }
    }

    private  InputStream getInputStream(String resourcePath) {
        var path = generatePath(resourcePath);
        try {
            return new FileInputStream(path);
        } catch (FileNotFoundException e) {
            System.out.println("File not found -> " + path);
            var defaultPath = getPath(resourcePath);
            try {
                return new FileInputStream(defaultPath);
            } catch (FileNotFoundException ex) {
                System.out.println("Default file not found");
                return null;
            }
        }
    }

    private String generatePath(String pattern) {
        return pattern.replaceAll(Pattern.quote("{{locale}}"), "_".concat(locale.toString()));
    }

    private String getPath(String pattern) {
        return pattern.replaceAll(Pattern.quote("{{locale}}"), StringUtils.EMPTY);
    }
}
