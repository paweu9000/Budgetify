package com.example.demo.history.model;

import com.example.demo.modelAbstract.BudgetEntity;
import com.example.demo.user.model.User;

import javax.persistence.*;
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

    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "history_user",
            joinColumns = @JoinColumn(name = "history_id"),
            inverseJoinColumns = @JoinColumn(name = "user_user_id"))
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public History(User user) {
        this.ID = UUID.randomUUID();
        this.transactions = new ArrayList<BudgetEntity>();
        this.user = user;
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
