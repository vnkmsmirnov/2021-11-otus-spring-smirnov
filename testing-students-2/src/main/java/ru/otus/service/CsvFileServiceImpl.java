package ru.otus.service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dto.Quiz;
import ru.otus.dto.QuizData;
import ru.otus.mapping.QuizDataMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class CsvFileServiceImpl implements CsvFileService {
    private final QuizDataMapper quizDataMapper;

    @Override
    public Quiz read(InputStream inputStream) {
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
            String [] nextLine;
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
}