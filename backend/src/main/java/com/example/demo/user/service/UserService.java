package com.example.demo.user.service;

import com.example.demo.dto.TransactionDto;
import com.example.demo.enums.BudgetType;
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

    public void updateUserBalance(User user, TransactionDto transactionDto) {
        BudgetType type = transactionDto.getBudgetType();
        switch(type) {
            case LOAN -> user.setLoan(user.getLoan() + transactionDto.getAmount());
            case INCOME -> user.setIncome(user.getIncome() + transactionDto.getAmount());
            case SAVINGS -> user.setSavings(user.getSavings() + transactionDto.getAmount());
            case SPENDINGS -> user.setSpendings(user.getSpendings() + transactionDto.getAmount());
            default -> throw new RuntimeException("Transaction has wrong type parameter!");
        }
        saveUser(user);
    }

}
