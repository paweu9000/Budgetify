package com.example.demo.loan;

import com.example.demo.enums.BudgetType;
import com.example.demo.modelAbstract.BudgetEntity;

public class Loan extends BudgetEntity {

    public Loan() {
    }

    public Loan(double amount) {
        super(amount);
        this.setName(BudgetType.LOAN);
    }
}
