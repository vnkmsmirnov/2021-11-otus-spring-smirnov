package ru.otus.library.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SequenceServiceImpl implements SequenceService {

    @Override
    public String get(SequencePrefix prefix) {
        return prefix.getName().concat(UUID.randomUUID().toString());
    }
}
