package com.example.demo.history.model;

import com.example.demo.modelAbstract.BudgetEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(name = "history")
public class History {

    @Id
    @Column(nullable = false, name = "id", columnDefinition = "VARCHAR(255)")
    private final UUID ID;

    @Column(name = "id")
    private ArrayList<BudgetEntity> transactions;

    public History() {
        this.ID = UUID.randomUUID();
        this.transactions = new ArrayList<BudgetEntity>();
    }

    public UUID getID() {
        return ID;
    }

    public ArrayList<BudgetEntity> getTransactions() {
        return transactions;
    }

    public void addTransaction(BudgetEntity budgetEntity) {
        this.transactions.add(budgetEntity);
    }
}
