package com.example.demo.transaction.service;

import com.example.demo.transaction.model.Transaction;
import com.example.demo.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction findById(long id) {
        return unwrapTransaction(id);
    }

    public Transaction unwrapTransaction(long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if(transaction.isPresent()) {
            return transaction.get();
        } else {
            throw new RuntimeException("Transaction with id: " + id + " does not exist!");
        }
    }
}
