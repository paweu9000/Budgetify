package com.example.demo.user.webcontroller;

import com.example.demo.user.model.User;
import com.example.demo.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.text.NumberFormat;

@RestController
@RequestMapping("/api/user")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/name")
    public ResponseEntity<String> getUsername(@CurrentSecurityContext(expression = "authentication?.name")
                                                  String username) {
        return new ResponseEntity<>(username, HttpStatus.OK);
    }

    @GetMapping("/email")
    public ResponseEntity<String> getEmail(@CurrentSecurityContext(expression = "authentication?.name")
                                              String username) {
        User user = userService.findByUsername(username);
        return new ResponseEntity<>(user.getEmail(), HttpStatus.OK);
    }

    @GetMapping("/balance")
    public ResponseEntity<Double> getBalance(@CurrentSecurityContext(expression = "authentication?.name")
                                                 String username) {
        User user = userService.findByUsername(username);
        return new ResponseEntity<>(user.getBalance(), HttpStatus.OK);
    }
}
