package com.example.demo.user.service;

import com.example.demo.user.model.User;
import com.example.demo.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

}
