package ru.otus.library.dao;

import ru.otus.library.model.AuthorEntity;

import java.util.List;

public interface AuthorDao {

    AuthorEntity findById(Long id);

    List<AuthorEntity> findAll();

    Long save(AuthorEntity author);
}
