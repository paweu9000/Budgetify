package com.example.demo;

import com.example.demo.user.service.UserService;
import com.example.demo.user.webcontroller.AuthController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationTests {

    @Autowired
    MockMvc mvc;

    @Mock
    UserService userService;

    @InjectMocks
    AuthController authController;

    @Test
    @WithAnonymousUser
    public void registerUserWithValidExistingDataTest() throws Exception {

        MockHttpServletRequestBuilder mockRequest = post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"login\": \"test_login\", \"username\": \"test_username\", " +
                                "\"email\": \"test@email.com\", \"password\": \"test_password\"}");
        mvc.perform(mockRequest).andExpect(status().isBadRequest());

    }

    @Test
    @WithAnonymousUser
    public void registerUserWithInvalidLoginTest() throws Exception {
        MockHttpServletRequestBuilder mockRequest = post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"login\": \"\", \"username\": \"test_username\", " +
                        "\"email\": \"test@email.com\", \"password\": \"test_password\"}");
        mvc.perform(mockRequest).andExpect(status().isBadRequest());
    }

    @Test
    @WithAnonymousUser
    public void loginUserWithValidUsernameTest() throws Exception {
        MockHttpServletRequestBuilder mockRequest = post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"usernameOrEmail\": \"test_username\", \"password\": \"test_password\"}");
        mvc.perform(mockRequest).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    public void loginUserWithValidEmailTest() throws Exception {
        MockHttpServletRequestBuilder mockRequest = post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"usernameOrEmail\": \"test@email.com\", \"password\": \"test_password\"}");
        mvc.perform(mockRequest).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    public void loginUserWithInvalidDataTest() throws Exception {
        MockHttpServletRequestBuilder mockRequest = post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"usernameOrEmail\": \"\", \"password\": \"test_password\"}");
        mvc.perform(mockRequest).andExpect(status().isBadRequest());
    }

    @Test
    @WithAnonymousUser
    public void loginUserWithInvalidPasswordTest() throws Exception {
        MockHttpServletRequestBuilder mockRequest = post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"usernameOrEmail\": \"test_username\", \"password\": \"\"}");
        mvc.perform(mockRequest).andExpect(status().isBadRequest());
    }

    @Test
    @WithAnonymousUser
    public void loginUserWithBlankPasswordTest() throws Exception {
        MockHttpServletRequestBuilder mockRequest = post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"usernameOrEmail\": \"test_username\", \"password\": \"   \"}");
        mvc.perform(mockRequest).andExpect(status().isBadRequest());
    }
}
