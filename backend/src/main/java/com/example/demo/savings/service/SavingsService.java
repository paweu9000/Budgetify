package com.example.demo.savings.service;

import com.example.demo.savings.model.Savings;
import com.example.demo.savings.repository.SavingsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SavingsService {
    final SavingsRepository repository;

    public SavingsService(SavingsRepository repository) {
        this.repository = repository;
    }

    public Savings findById(UUID id) {
        return unwrapSavings(id);
    }
    public Savings unwrapSavings(UUID id) {
        Optional<Savings> savings = repository.findById(id);
        if (savings.isPresent()) {
            return savings.get();
        } else {
            throw new RuntimeException("Savings object with id: " + id.toString() + " does not exist!");
        }
    }
}
