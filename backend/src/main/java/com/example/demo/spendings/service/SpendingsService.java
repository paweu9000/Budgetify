package com.example.demo.spendings.service;

import com.example.demo.spendings.model.Spendings;
import com.example.demo.spendings.repository.SpendingsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SpendingsService{

    final SpendingsRepository repository;

    public SpendingsService(SpendingsRepository repository) {
        this.repository = repository;
    }

    public Spendings findById(UUID id) {
        return unwrapSpendings(id);
    }
    public Spendings unwrapSpendings(UUID id) {
        Optional<Spendings> spendings = repository.findById(id);
        if (spendings.isPresent()) {
            return spendings.get();
        } else {
            throw new RuntimeException("Savings object with id: " + id.toString() + " does not exist!");
        }
    }
}
