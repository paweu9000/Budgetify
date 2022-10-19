package com.example.demo.dto;

import com.example.demo.enums.BudgetType;
import com.example.demo.user.model.User;

import java.time.LocalDate;


public class TransactionDto {

    private String description;

    private BudgetType budgetType;

    private double amount;

    private LocalDate date;

    public String getDescription() {
        return description;
    }

    public BudgetType getBudgetType() {
        return budgetType;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }
}
