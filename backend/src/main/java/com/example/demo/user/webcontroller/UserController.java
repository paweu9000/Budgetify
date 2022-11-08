package com.example.demo.user.webcontroller;

import com.example.demo.dao.UserDao;
import com.example.demo.user.model.User;
import com.example.demo.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/savings")
    public ResponseEntity<Double> getSavings(@CurrentSecurityContext(expression = "authentication?.name")
                                                 String username) {
        User user = userService.findByUsername(username);
        return new ResponseEntity<>(user.getSavings(), HttpStatus.OK);
    }

    @GetMapping("/loan")
    public ResponseEntity<Double> getLoan(@CurrentSecurityContext(expression = "authentication?.name")
                                                String username) {
        User user = userService.findByUsername(username);
        return new ResponseEntity<>(user.getLoan(), HttpStatus.OK);
    }

    @GetMapping("/spendings")
    public ResponseEntity<Double> getSpendings(@CurrentSecurityContext(expression = "authentication?.name")
                                               String username){
        User user = userService.findByUsername(username);
        return new ResponseEntity<>(user.getSpendings(), HttpStatus.OK);
    }

    @GetMapping("/income")
    public ResponseEntity<Double> getIncome(@CurrentSecurityContext(expression = "authentication?.name")
                                               String username){
        User user = userService.findByUsername(username);
        return new ResponseEntity<>(user.getIncome(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<UserDao> getUser(@CurrentSecurityContext(expression = "authentication?.name")
                                               String username) {
        User user = userService.findByUsername(username);
        return new ResponseEntity<>(userService.toDao(user), HttpStatus.OK);
    }
}
