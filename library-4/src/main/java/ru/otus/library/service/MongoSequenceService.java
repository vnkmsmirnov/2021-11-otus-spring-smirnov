package ru.otus.library.service;

public interface MongoSequenceService {

    String getSequence(SequencePrefix prefix);
}
