package com.example.demo.transaction.webcontroller;

import com.example.demo.dto.TransactionDto;
import com.example.demo.enums.BudgetType;
import com.example.demo.transaction.model.Transaction;
import com.example.demo.transaction.service.TransactionService;
import com.example.demo.user.model.User;
import com.example.demo.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
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

    @GetMapping("/all")
    public ResponseEntity<String> getAllTransactions(@CurrentSecurityContext(expression = "authentication?.name")
                                                     String username) {
        User user = userService.findByUsername(username);
        List<Transaction> transactions = transactionService.findAllByUser(user);
        return new ResponseEntity<>(transactions.toString(), HttpStatus.OK);
    }

    @GetMapping("/loan/all")
    public ResponseEntity<String> getAllLoanTransactions(@CurrentSecurityContext(expression = "authentication?.name")
                                                             String username) {
        User user = userService.findByUsername(username);
        List<Transaction> transactions = transactionService.findAllByUserAndType(user, BudgetType.LOAN);
        return new ResponseEntity<>(transactions.toString(), HttpStatus.OK);
    }

    @GetMapping("/spendings/all")
    public ResponseEntity<String> getAllSpendingsTransactions(@CurrentSecurityContext(expression = "authentication?.name")
                                                         String username) {
        User user = userService.findByUsername(username);
        List<Transaction> transactions = transactionService.findAllByUserAndType(user, BudgetType.SPENDINGS);
        return new ResponseEntity<>(transactions.toString(), HttpStatus.OK);
    }

    @GetMapping("/savings/all")
    public ResponseEntity<String> getAllSavingsTransactions(@CurrentSecurityContext(expression = "authentication?.name")
                                                              String username) {
        User user = userService.findByUsername(username);
        List<Transaction> transactions = transactionService.findAllByUserAndType(user, BudgetType.SAVINGS);
        return new ResponseEntity<>(transactions.toString(), HttpStatus.OK);
    }
}
