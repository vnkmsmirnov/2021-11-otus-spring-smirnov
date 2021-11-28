package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.props.AppProperties;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class ResourceFileServiceImpl implements ResourceFileService {
    private final AppProperties appProperties;

    public InputStream getInputStream() {
        return getClass()
                .getClassLoader()
                .getResourceAsStream(appProperties.getPath());
    }
}
