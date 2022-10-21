package com.example.demo.user.service;

import com.example.demo.dto.TransactionDto;
import com.example.demo.enums.BudgetType;
import com.example.demo.user.model.User;
import com.example.demo.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

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

    private void deductSpendingsFromBalance(User user, Double amount) {
        double updatedBalance = user.getBalance() - amount;
        double formattedUpdatedBalance = round(updatedBalance, 2);
        user.setBalance(formattedUpdatedBalance);
    }

    private void addIncomeToBalance(User user, Double amount) {
        double updatedBalance = user.getBalance() + amount;
        double formattedUpdatedBalance = round(updatedBalance, 2);
        user.setBalance(formattedUpdatedBalance);
    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException("Parameter places cannot be less than 0!");
        BigDecimal bigDecimal = BigDecimal.valueOf(value);
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    public void updateUserBalance(User user, TransactionDto transactionDto) {
        BudgetType type = transactionDto.getBudgetType();
        switch (type) {
            case LOAN -> user.setLoan(user.getLoan() + transactionDto.getAmount());
            case INCOME -> {
                user.setIncome(user.getIncome() + transactionDto.getAmount());
                addIncomeToBalance(user, transactionDto.getAmount());
            }
            case SAVINGS -> user.setSavings(user.getSavings() + transactionDto.getAmount());
            case SPENDINGS -> {
                user.setSpendings(user.getSpendings() + transactionDto.getAmount());
                deductSpendingsFromBalance(user, transactionDto.getAmount());
            }
            default -> throw new RuntimeException("Transaction has wrong type parameter!");
        }
        saveUser(user);
    }

}
