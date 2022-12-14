package com.example.demo.user.service;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.TransactionDto;
import com.example.demo.enums.BudgetType;
import com.example.demo.transaction.model.Transaction;
import com.example.demo.user.model.User;
import com.example.demo.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        return unwrapUser(userRepository.findByEmail(email));
    }

    public User findByUsername(String username) {
        return unwrapUser(userRepository.findByUsername(username));
    }

    public User unwrapUser(Optional<User> user) {
        if(user.isEmpty()) throw new RuntimeException("User does not exist!");
        return user.get();
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    private void deductSpendingsFromBalance(User user, Double amount) {
        double updatedBalance = user.getBalance() - amount;
        double formattedUpdatedBalance = round(updatedBalance);
        user.setBalance(formattedUpdatedBalance);
    }

    private void addIncomeToBalance(User user, Double amount) {
        double updatedBalance = user.getBalance() + amount;
        double formattedUpdatedBalance = round(updatedBalance);
        user.setBalance(formattedUpdatedBalance);
    }

    private double round(double value) {
        BigDecimal bigDecimal = BigDecimal.valueOf(value);
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
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

    public void updateUserBeforeTransactionRemoval(User user, Transaction transaction) {
        BudgetType type = transaction.getBudgetType();
        switch (type) {
            case LOAN -> user.setLoan(user.getLoan() - transaction.getAmount());
            case INCOME -> {
                user.setIncome(user.getIncome() - transaction.getAmount());
                deductSpendingsFromBalance(user, transaction.getAmount());
            }
            case SAVINGS -> user.setSavings(user.getSavings() - transaction.getAmount());
            case SPENDINGS -> {
                user.setSpendings(user.getSpendings() - transaction.getAmount());
                addIncomeToBalance(user, transaction.getAmount());
            }
            default -> throw new RuntimeException("Transaction has wrong type parameter!");
        }
        saveUser(user);
    }

    public UserDao toDao(User user) {
        return new UserDao(user);
    }

}
