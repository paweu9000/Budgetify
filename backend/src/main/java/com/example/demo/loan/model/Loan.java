package com.example.demo.loan.model;

import com.example.demo.enums.BudgetType;
import com.example.demo.modelAbstract.BudgetEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "loan")
public class Loan extends BudgetEntity {

    public Loan() {
    }

    public Loan(double amount) {
        super(amount);
        this.setName(BudgetType.LOAN);
    }
}