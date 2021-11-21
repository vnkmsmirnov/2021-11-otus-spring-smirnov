package ru.otus.service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.lang3.StringUtils;
import ru.otus.dto.QuestionAndAnswers;
import ru.otus.dto.Quiz;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class FileServiceCsv implements FileService {

    @Override
    public Quiz read(String path) {
        if (StringUtils.isEmpty(path)) {
            return Quiz.builder().build();
        }
        var inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream(path);
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
            var result = new ArrayList<QuestionAndAnswers>();
            while ((nextLine = reader.readNext()) != null) {
                    result.add(QuestionAndAnswers.builder()
                                    .question(nextLine[0])
                                    .answers(Arrays.asList(nextLine[1].split(",")))
                                    .build());
                }
            return Quiz.builder()
                    .questionAndAnswers(result)
                    .build();
        } catch (IOException | CsvException e) {
            return Quiz.builder().build();
        }
    }
}