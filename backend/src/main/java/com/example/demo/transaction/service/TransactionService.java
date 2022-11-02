package com.example.demo.transaction.service;

import com.example.demo.dto.TransactionDto;
import com.example.demo.enums.BudgetType;
import com.example.demo.transaction.model.Transaction;
import com.example.demo.transaction.repository.TransactionRepository;
import com.example.demo.user.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
            return new Transaction();
        }
    }

    public void deleteTransaction(Transaction transaction) {
        transactionRepository.delete(transaction);
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

    public List<Transaction> findAllByUser(User user) {
        return transactionRepository.findAll()
                .stream()
                .filter(transaction -> transaction.getUser()
                        .equals(user)).toList();
    }

    public List<Transaction> findAllByUserAndType(User user, BudgetType budgetType) {
        return transactionRepository.findAll()
                .stream()
                .filter(transaction -> transaction.getUser().equals(user)
                        && transaction.getBudgetType() == budgetType).toList();
    }

    public List<Transaction> findAllTransactionsByDays(User user, int days) {
        long currentDay = LocalDate.now().toEpochDay();
        return transactionRepository.findAll()
                .stream()
                .filter(transaction -> transaction.getUser().equals(user) &&
                        currentDay - transaction.getDate().toEpochDay() <= days).toList();
    }

    public void editTransaction(Transaction transaction, TransactionDto transactionDto) {
        transaction.setBudgetType(transactionDto.getBudgetType());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setDescription(transactionDto.getDescription());
        transactionRepository.save(transaction);
    }

}
