package com.example.demo.spendings.service;

import com.example.demo.spendings.repository.SpendingsRepository;
import org.springframework.stereotype.Service;

@Service
public class SpendingsService{

    final SpendingsRepository repository;

    public SpendingsService(SpendingsRepository repository) {
        this.repository = repository;
    }
}
