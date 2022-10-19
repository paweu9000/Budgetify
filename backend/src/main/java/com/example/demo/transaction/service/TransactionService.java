package com.example.demo.transaction.service;

import com.example.demo.dto.TransactionDto;
import com.example.demo.transaction.model.Transaction;
import com.example.demo.transaction.repository.TransactionRepository;
import com.example.demo.user.model.User;
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

    public void saveTransaction(Transaction transaction) {
        this.transactionRepository.save(transaction);
    }

    public Transaction readDto(TransactionDto transactionDto, User user) {
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setDescription(transactionDto.getDescription());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setBudgetType(transactionDto.getBudgetType());
        return transaction;
    }
}
