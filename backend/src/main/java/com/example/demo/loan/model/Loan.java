package com.example.demo.loan.model;

import com.example.demo.enums.BudgetType;
import com.example.demo.modelAbstract.BudgetEntity;
import com.example.demo.user.model.User;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "loan")
public class Loan extends BudgetEntity {

    @OneToOne
    private User user;

    public Loan() {
    }

    public Loan(double amount) {
        super(amount);
        this.setName(BudgetType.LOAN);
    }
}
