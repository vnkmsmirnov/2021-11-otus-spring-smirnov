package ru.otus.library.mapping;

import ru.otus.library.dto.User;
import ru.otus.library.model.UserEntity;

public interface UserMapper {

    User fromEntity(UserEntity userEntity);
}
