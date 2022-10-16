package com.example.demo.income.model;

import com.example.demo.enums.BudgetType;
import com.example.demo.modelAbstract.BudgetEntity;
import com.example.demo.user.model.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "income")
public class Income extends BudgetEntity {

    @OneToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User user;

    public Income() {
    }

    public Income(double amount) {
        super(amount);
        this.setName(BudgetType.INCOME);
    }
}
