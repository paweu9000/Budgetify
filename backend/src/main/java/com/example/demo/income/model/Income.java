package com.example.demo.income.model;

import com.example.demo.enums.BudgetType;
import com.example.demo.modelAbstract.BudgetEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "income")
public class Income extends BudgetEntity {

    public Income() {
    }

    public Income(double amount) {
        super(amount);
        this.setName(BudgetType.INCOME);
    }
}
