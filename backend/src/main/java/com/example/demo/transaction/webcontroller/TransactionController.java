package com.example.demo.transaction.webcontroller;

import com.example.demo.dao.TransactionDao;
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

import javax.validation.Valid;
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
    public ResponseEntity<TransactionDao> getTransaction(@PathVariable("transactionId") long transactionId,
                                                         Principal principal) {
        Transaction transaction = transactionService.findById(transactionId);
        if(transaction.getUser() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (transaction.getUser().getUsername().equals(principal.getName())){
            return new ResponseEntity<>(transactionService.toDao(transaction), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable("transactionId") long transactionId,
                                                    Principal principal) {
        Transaction transaction = transactionService.findById(transactionId);
        if(transaction.getUser() == null) {
            return new ResponseEntity<>("Transaction does not exist!", HttpStatus.BAD_REQUEST);
        } else if (transaction.getUser().getUsername().equals(principal.getName())){
            userService.updateUserBeforeTransactionRemoval(transaction.getUser(), transaction);
            transactionService.deleteTransaction(transaction);
            return new ResponseEntity<>("Transaction successfully deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("You cannot access other users data!", HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<String> editTransaction(@RequestBody @Valid TransactionDto transactionDto,
                                                  @PathVariable("transactionId") long transactionId,
                                                  Principal principal) {
        Transaction transaction = transactionService.findById(transactionId);
        if(transaction.getUser() == null) {
            return new ResponseEntity<>("Transaction does not exist!", HttpStatus.BAD_REQUEST);
        } else if (transaction.getUser().getUsername().equals(principal.getName())){
            userService.updateUserBeforeTransactionRemoval(transaction.getUser(), transaction);
            transactionService.editTransaction(transaction, transactionDto);
            userService.updateUserBalance(transaction.getUser(), transactionDto);
            return new ResponseEntity<>("Transaction successfully edited", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("You cannot access other users data!", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("")
    public ResponseEntity<String> createTransaction(@CurrentSecurityContext(expression = "authentication?.name")
                                                    String username,
                                                    @Valid @RequestBody TransactionDto transactionDto) {
        User user = userService.findByUsername(username);
        Transaction transaction = transactionService.readDto(transactionDto, user);
        transactionService.saveTransaction(transaction);
        userService.updateUserBalance(user, transactionDto);
        return new ResponseEntity<>(transaction.toString(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TransactionDao>> getAllTransactions(@CurrentSecurityContext(expression = "authentication?.name")
                                                     String username) {
        User user = userService.findByUsername(username);
        List<TransactionDao> transactions = transactionService.toDaoList(transactionService.findAllByUser(user));
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/all/{days}")
    public ResponseEntity<List<TransactionDao>> getTransactionsByDays(@PathVariable("days") int days, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<TransactionDao> transactions = transactionService.toDaoList
                (transactionService.findAllTransactionsByDays(user, days));
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/loan/all")
    public ResponseEntity<List<TransactionDao>> getAllLoanTransactions(@CurrentSecurityContext(expression = "authentication?.name")
                                                             String username) {
        User user = userService.findByUsername(username);
        List<TransactionDao> transactions = transactionService.toDaoList
                (transactionService.findAllByUserAndType(user, BudgetType.LOAN));
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/spendings/all")
    public ResponseEntity<List<TransactionDao>> getAllSpendingsTransactions(
            @CurrentSecurityContext(expression = "authentication?.name")
                                                         String username) {
        User user = userService.findByUsername(username);
        List<TransactionDao> transactions = transactionService.toDaoList
                (transactionService.findAllByUserAndType(user, BudgetType.SPENDINGS));
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/savings/all")
    public ResponseEntity<List<TransactionDao>> getAllSavingsTransactions(
            @CurrentSecurityContext(expression = "authentication?.name")
                                                              String username) {
        User user = userService.findByUsername(username);
        List<TransactionDao> transactions = transactionService.toDaoList
                (transactionService.findAllByUserAndType(user, BudgetType.SAVINGS));
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/income/all")
    public ResponseEntity<List<TransactionDao>> getAllIncomeTransactions(
            @CurrentSecurityContext(expression = "authentication?.name")
                                                            String username) {
        User user = userService.findByUsername(username);
        List<TransactionDao> transactions = transactionService.toDaoList
                (transactionService.findAllByUserAndType(user, BudgetType.INCOME));
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
