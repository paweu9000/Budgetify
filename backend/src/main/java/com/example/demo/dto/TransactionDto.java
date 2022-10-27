package com.example.demo.dto;

import com.example.demo.enums.BudgetType;
import com.example.demo.user.model.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;


public class TransactionDto {

    @NotEmpty
    @Size(min = 5, message = "Description should have at least 5 characters!")
    private String description;

    @NotEmpty(message = "Type of transaction needs to be defined!")
    private BudgetType budgetType;

    @NotEmpty
    private double amount;
    private LocalDate date;

    public String getDescription() {
        return description;
    }

    public BudgetType getBudgetType() {
        return budgetType;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }
}
