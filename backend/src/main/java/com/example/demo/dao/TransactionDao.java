package com.example.demo.dao;

import com.example.demo.enums.BudgetType;

import java.time.LocalDate;

public class TransactionDao {
    private String description;
    private BudgetType budgetType;
    private double amount;
    private LocalDate date;

    public TransactionDao() {
    }

    public TransactionDao(String description, BudgetType budgetType, double amount, LocalDate date) {
        this.description = description;
        this.budgetType = budgetType;
        this.amount = amount;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BudgetType getBudgetType() {
        return budgetType;
    }

    public void setBudgetType(BudgetType budgetType) {
        this.budgetType = budgetType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
