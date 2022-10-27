package com.example.demo.dto;

import com.example.demo.enums.BudgetType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;


public class TransactionDto {

    @NotEmpty(message = "Description cannot be empty!")
    @NotBlank(message = "Description cannot be blank!")
    @Size(min = 5, message = "Description should have at least 5 characters!")
    private String description;
    @NotNull(message = "Type of transaction must be defined!")
    private BudgetType budgetType;

    @NotNull(message = "You need to provide an amount!")
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
