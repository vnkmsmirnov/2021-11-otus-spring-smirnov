package ru.otus.library.mapping;

import org.springframework.stereotype.Component;
import ru.otus.library.dto.User;
import ru.otus.library.model.UserEntity;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User fromEntity(UserEntity userEntity) {
        return User.builder()
                .email(userEntity.getUsername())
                .name(userEntity.getName())
                .roles(userEntity.getRoles())
                .build();
    }
}
