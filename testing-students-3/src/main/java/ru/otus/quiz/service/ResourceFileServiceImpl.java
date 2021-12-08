package ru.otus.quiz.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import ru.otus.quiz.exception.QuizException;
import ru.otus.quiz.properties.AppProperties;

import java.io.*;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ResourceFileServiceImpl implements ResourceFileService {
    private final AppProperties appProperties;
    private final Locale locale = LocaleContextHolder.getLocale();

    public InputStream getInputStream() throws QuizException {
        var path = generatePath(appProperties.getPath());
        try {
            return new FileInputStream(path);
        } catch (FileNotFoundException e) {
            System.out.println("File not found -> " + path);
            var defaultPath = getPath(appProperties.getPath());
            try {
                return new FileInputStream(defaultPath);
            } catch (FileNotFoundException ex) {
                System.out.println("Default file not found");
                throw new QuizException("You can't continue the quiz", ex);
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
