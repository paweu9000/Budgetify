package com.example.demo.income.service;

import com.example.demo.income.repository.IncomeRepository;
import org.springframework.stereotype.Service;

@Service
public class IncomeService {
    final IncomeRepository repository;

    public IncomeService(IncomeRepository repository) {
        this.repository = repository;
    }
}
