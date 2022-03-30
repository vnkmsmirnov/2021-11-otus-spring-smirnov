package ru.otus.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);
}
