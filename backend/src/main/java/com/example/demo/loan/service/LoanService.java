package com.example.demo.loan.service;

import com.example.demo.loan.repository.LoanRepository;
import org.springframework.stereotype.Service;

@Service
public class LoanService {
    final LoanRepository repository;

    public LoanService(LoanRepository repository) {
        this.repository = repository;
    }
}
