package com.example.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginDto {
    @NotEmpty(message = "Field cant be empty")
    @NotBlank(message = "Field cannot be blank!")
    @Size(min = 5, message = "Please provide correct credentials!")
    private String usernameOrEmail;

    @NotEmpty(message = "Incorrect password!")
    @NotBlank(message = "Incorrect password!")
    @Size(min = 8, message = "Incorrect password!")
    private String password;

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }
}
