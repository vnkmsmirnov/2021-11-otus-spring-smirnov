package ru.otus.library.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MongoSequenceServiceImpl implements MongoSequenceService {

    @Override
    public String getSequence(SequencePrefix prefix) {
        return prefix.getName().concat(UUID.randomUUID().toString());
    }
}
