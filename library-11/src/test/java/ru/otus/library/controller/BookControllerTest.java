package ru.otus.library.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import ru.otus.library.config.SecurityConfig;
import ru.otus.library.service.UserService;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Import(SecurityConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser
    @Test
    public void getTest() throws Exception {
        mockMvc.perform(get("/api/book/1"))
                .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    public void getAllTest() throws Exception {
        mockMvc.perform(get("/api/book"))
                .andExpect(status().isOk());
    }

    @WithMockUser(authorities = {"ROLE_ADMIN"})
    @Transactional
    @Test
    public void saveAuthorityTest() throws Exception {
        mockMvc.perform(post("/api/book")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"TEST_TITLE\",\"pages\": 1,\"genre\": {\"name\": \"TEST_GENRE\"},\"author\": {\"firstName\": \"TEST_FIRST_NAME\",\"lastName\": \"TEST_LAST_NAME\"}}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser(authorities = {"ROLE_USER"})
    @Test
    public void saveNotAuthorityTest() throws Exception {
        mockMvc.perform(post("/api/book")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"TEST_TITLE\",\"pages\": 1,\"genre\": {\"name\": \"TEST_GENRE\"},\"author\": {\"firstName\": \"TEST_FIRST_NAME\",\"lastName\": \"TEST_LAST_NAME\"}}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = {"ROLE_ADMIN"})
    @Transactional
    @Test
    public void deleteAuthorityTest() throws Exception {
        mockMvc.perform(delete("/api/book/1"))
                .andExpect(status().isOk());
    }

    @WithMockUser(authorities = {"ROLE_USER"})
    @Test
    public void deleteNotAuthorityTest() throws Exception {
        mockMvc.perform(delete("/api/book/1"))
                .andExpect(status().isForbidden());
    }
}
