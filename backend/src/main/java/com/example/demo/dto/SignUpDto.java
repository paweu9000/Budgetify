package com.example.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SignUpDto {

    @NotEmpty
    @Size(min = 5, message = "Login should have at least 5 characters!")
    private String login;

    @NotEmpty
    @Size(min = 4, message = "Username should have at least 4 characters!")
    private String username;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
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
