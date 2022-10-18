package com.example.demo.transaction.webcontroller;

import com.example.demo.transaction.model.Transaction;
import com.example.demo.transaction.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("api/transaction")
public class TransactionController {

    final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable long id) {
        Transaction transaction = transactionService.findById(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }
}
