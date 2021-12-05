package ru.otus.quiz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {
    private final MessageSource messageSource;
    private final Locale locale = LocaleContextHolder.getLocale();

    @Override
    public String get(String key) {
        return messageSource.getMessage(key, new String[] {}, locale);
    }

    @Override
    public String get(String key, String... params) {
        return messageSource.getMessage(key, Stream.of(params).toArray(String[]::new), locale);
    }
}
