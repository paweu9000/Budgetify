package com.example.demo.transaction.model;

import com.example.demo.enums.BudgetType;
import com.example.demo.user.model.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column
    private BudgetType budgetType;

    @Column
    private double amount;

    @Column
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User user;

    public Transaction() {
        this.date = LocalDate.now();
    }

    public Transaction(String description, BudgetType budgetType, double amount, User user) {
        this.description = description;
        this.budgetType = budgetType;
        this.amount = amount;
        this.date = LocalDate.now();
        this.user = user;
    }

    public long getId() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", budgetType=" + budgetType +
                ", amount=" + amount +
                ", date=" + date +
                ", user=" + user.getUsername() +
                '}';
    }
}
