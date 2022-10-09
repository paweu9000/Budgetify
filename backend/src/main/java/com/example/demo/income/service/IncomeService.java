package com.example.demo.income.service;

import com.example.demo.income.model.Income;
import com.example.demo.income.repository.IncomeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class IncomeService {
    final IncomeRepository repository;

    public IncomeService(IncomeRepository repository) {
        this.repository = repository;
    }

    public Income findById(UUID uuid) {
        return unwrapIncome(uuid);
    }

    public Income unwrapIncome(UUID uuid) {
        Optional<Income> income = repository.findById(uuid);
        if (income.isPresent()) {
            return income.get();
        } else {
            throw new RuntimeException("Income with id: " + uuid.toString() + " does not exist!");
        }
    }
}
