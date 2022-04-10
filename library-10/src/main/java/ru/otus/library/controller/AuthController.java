package ru.otus.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.library.dto.User;
import ru.otus.library.mapping.UserMapper;
import ru.otus.library.model.UserEntity;
import ru.otus.library.service.UserService;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public User login(@RequestBody UserEntity user, HttpServletRequest request) {
        var userEntity = (UserEntity) userService.loadUserByUsername(user.getEmail());
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userEntity, user.getPassword(),
                userEntity.getAuthorities());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        var session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        return userMapper.fromEntity(userEntity);
    }
}
