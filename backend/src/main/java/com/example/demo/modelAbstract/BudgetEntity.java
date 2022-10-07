package com.example.demo.modelAbstract;

import com.example.demo.enums.BudgetType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public abstract class BudgetEntity {

    @Id
    @Column(nullable = false, name = "id", columnDefinition = "VARCHAR(255)")
    private final UUID ID;

    @Column(nullable = false, name = "amount")
    private double amount;

    @Column(nullable = false)
    private BudgetType name;

    @Column(nullable = false)
    private LocalDate date;

    public BudgetEntity() {
        this.ID = UUID.randomUUID();
    }

    public BudgetEntity(double amount) {
        this.ID = UUID.randomUUID();
        this.amount = amount;
        this.date = LocalDate.now();
        this.name = BudgetType.PLACEHOLDER;
    }

    public UUID getId() {
        return ID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public BudgetType getName() {
        return name;
    }

    public void setName(BudgetType name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "BudgetEntity{" +
                "id=" + ID.toString() +
                ", amount=" + amount +
                ", name=" + name +
                ", date=" + date +
                '}';
    }
}