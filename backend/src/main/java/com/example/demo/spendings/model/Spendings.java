package com.example.demo.spendings.model;

import com.example.demo.enums.BudgetType;
import com.example.demo.modelAbstract.BudgetEntity;
import com.example.demo.user.model.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "spendings")
public class Spendings extends BudgetEntity {

    @OneToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User user;

    public Spendings() {
    }

    public Spendings(double amount) {
        super(amount);
        this.setName(BudgetType.SAVINGS);
    }
}
