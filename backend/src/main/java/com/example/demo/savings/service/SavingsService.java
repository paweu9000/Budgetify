package com.example.demo.savings.service;

import com.example.demo.savings.repository.SavingsRepository;
import org.springframework.stereotype.Service;

@Service
public class SavingsService {
    final SavingsRepository repository;

    public SavingsService(SavingsRepository repository) {
        this.repository = repository;
    }
}
