package ru.otus.library.service;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SequencePrefix {
    BOOK("b_"),
    AUTHOR("a_"),
    GENRE("g_"),
    COMMENT("c_");

    @Getter
    private final String name;
}