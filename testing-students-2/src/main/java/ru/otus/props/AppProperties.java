package ru.otus.props;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@PropertySource("classpath:/application.properties")
@Component
public class AppProperties {
    private final String path;
    private final Integer correctAnswers;

    public AppProperties(@Value("${app.path}") String path,
                         @Value("${app.correct-answers}") Integer correctAnswers) {
        this.path = path;
        this.correctAnswers = correctAnswers;
    }
}
