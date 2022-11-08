package com.example.demo.dao;

import com.example.demo.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;

public class UserDao {

    private final String username;
    private final String email;
    private final double balance;
    private final double income;
    private final double loan;
    private final double savings;
    private final double spendings;

    public UserDao(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.balance = user.getBalance();
        this.income = user.getIncome();
        this.loan = user.getLoan();
        this.savings = user.getSavings();
        this.spendings = user.getSpendings();
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }

    public double getIncome() {
        return income;
    }

    public double getLoan() {
        return loan;
    }

    public double getSavings() {
        return savings;
    }

    public double getSpendings() {
        return spendings;
    }
}
