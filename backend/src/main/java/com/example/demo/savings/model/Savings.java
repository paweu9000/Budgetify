package com.example.demo.savings.model;

import com.example.demo.enums.BudgetType;
import com.example.demo.modelAbstract.BudgetEntity;
import com.example.demo.user.model.User;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "savings")
public class Savings extends BudgetEntity {

    @OneToOne
    @JoinColumn(name = "users_id", nullable = false)
    User user;

    public Savings() {
    }

    public Savings(double amount) {
        super(amount);
        this.setName(BudgetType.SAVINGS);
    }
}
