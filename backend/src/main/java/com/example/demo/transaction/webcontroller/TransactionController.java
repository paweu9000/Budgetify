package com.example.demo.transaction.webcontroller;

import com.example.demo.dto.TransactionDto;
import com.example.demo.transaction.model.Transaction;
import com.example.demo.transaction.service.TransactionService;
import com.example.demo.user.model.User;
import com.example.demo.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("api/transaction")
public class TransactionController {

    final TransactionService transactionService;
    final UserService userService;

    public TransactionController(TransactionService transactionService, UserService userService) {
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable long id) {
        Transaction transaction = transactionService.findById(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> createTransaction(@CurrentSecurityContext(expression = "authentication?.name")
                                                    String email, @RequestBody TransactionDto transactionDto) {
        User user = userService.findByEmail(email);
        Transaction transaction = transactionService.readDto(transactionDto, user);
        transactionService.saveTransaction(transaction);
        return new ResponseEntity<>(transaction.toString(), HttpStatus.OK);
    }
}
