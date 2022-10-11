package com.example.demo.transaction.model;

import com.example.demo.enums.BudgetType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @Column(nullable = false)
    private UUID id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column
    private BudgetType budgetType;

    @Column
    private double amount;

    @Column
    private LocalDate date;

    public Transaction() {
        this.id = UUID.randomUUID();
    }

    public Transaction(String description, BudgetType budgetType, double amount, LocalDate date) {
        this.id = UUID.randomUUID();
        this.description = description;
        this.budgetType = budgetType;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    public UUID getId() {
        return id;
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
