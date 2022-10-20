package com.example.demo.transaction.webcontroller;

import com.example.demo.dto.TransactionDto;
import com.example.demo.transaction.model.Transaction;
import com.example.demo.transaction.service.TransactionService;
import com.example.demo.user.model.User;
import com.example.demo.user.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @GetMapping("/{transactionId}")
    public ResponseEntity<String> getTransaction(@PathVariable("transactionId") long transactionId,
                                                 Principal principal) {
        Transaction transaction = transactionService.findById(transactionId);
        if(transaction.getUser().getUsername().equals(principal.getName())) {
            return new ResponseEntity<>(transaction.toString(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("You cannot access other users data!", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("")
    public ResponseEntity<String> createTransaction(@CurrentSecurityContext(expression = "authentication?.name")
                                                    String username, @RequestBody TransactionDto transactionDto) {
        User user = userService.findByUsername(username);
        Transaction transaction = transactionService.readDto(transactionDto, user);
        transactionService.saveTransaction(transaction);
        userService.updateUserBalance(user, transactionDto);
        return new ResponseEntity<>(transaction.toString(), HttpStatus.OK);
    }
}
