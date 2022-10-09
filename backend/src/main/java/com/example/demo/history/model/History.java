package com.example.demo.history.model;

import com.example.demo.modelAbstract.BudgetEntity;
import com.example.demo.user.model.User;

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
    private UUID ID;

    @Column(name = "transactions")
    private ArrayList<BudgetEntity> transactions;

    @Column(name = "owner", nullable = false)
    private User owner;

    public History(User user) {
        this.ID = UUID.randomUUID();
        this.transactions = new ArrayList<BudgetEntity>();
        this.owner = user;
    }

    public History() {
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
