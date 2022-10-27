package com.example.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SignUpDto {

    @NotEmpty(message = "Login cannot be empty!")
    @NotBlank(message = "Login cannot be blank!")
    @Size(min = 5, message = "Login should have at least 5 characters!")
    private String login;

    @NotEmpty(message = "Username cannot be empty!")
    @NotBlank(message = "Username cannot be blank!")
    @Size(min = 4, message = "Username should have at least 4 characters!")
    private String username;

    @NotEmpty(message = "Email cannot be empty!")
    @NotBlank(message = "Email cannot be blank!")
    @Email(message = "It must be valid email address!")
    private String email;

    @NotEmpty(message = "Password cannot be empty!")
    @NotBlank(message = "Password cannot be blank!")
    @Size(min = 8, message = "Password should have at least 8 characters!")
    private String password;

    public String getLogin() {
        return login;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
