package ru.otus.library.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void authTest() {
        ResponseEntity<String> response = template
                .withBasicAuth("admin@mail.com", "developer")
                .getForEntity("/api/genre", String.class);

        assertNotEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}