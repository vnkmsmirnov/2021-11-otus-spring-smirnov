package ru.otus.library.mapping;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.otus.library.dto.User;
import ru.otus.library.model.UserEntity;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User fromEntity(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getUsername())
                .build();
    }
}
