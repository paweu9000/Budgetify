package com.example.demo.spendings.model;

import com.example.demo.enums.BudgetType;
import com.example.demo.modelAbstract.BudgetEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "spendings")
public class Spendings extends BudgetEntity {
    public Spendings() {
    }

    public Spendings(double amount) {
        super(amount);
        this.setName(BudgetType.SAVINGS);
    }
}