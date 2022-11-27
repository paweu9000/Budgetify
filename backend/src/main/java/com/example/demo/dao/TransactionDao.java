package com.example.demo.dao;

import com.example.demo.enums.BudgetType;
import com.example.demo.transaction.model.Transaction;

import java.time.LocalDate;

public class TransactionDao {

    private long id;
    private String description;
    private BudgetType budgetType;
    private double amount;
    private LocalDate date;

    public TransactionDao() {
    }

    public TransactionDao(Transaction transaction) {
        this.id = transaction.getId();
        this.description = transaction.getDescription();
        this.budgetType = transaction.getBudgetType();
        this.amount = transaction.getAmount();
        this.date = transaction.getDate();
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
