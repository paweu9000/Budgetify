package com.example.demo.loan.service;

import com.example.demo.loan.model.Loan;
import com.example.demo.loan.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LoanService {
    final LoanRepository repository;

    public LoanService(LoanRepository repository) {
        this.repository = repository;
    }

    public Loan findById(UUID id) {
        return unwrapLoan(id);
    }

    public Loan unwrapLoan(UUID id) {
        Optional<Loan> loan = repository.findById(id);
        if (loan.isPresent()) {
            return loan.get();
        } else {
            throw new RuntimeException("Loan object with id: " + id.toString() + " does not exist!");
        }
    }
}
