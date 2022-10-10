package com.example.demo.loan.webcontroller;

import com.example.demo.loan.model.Loan;
import com.example.demo.loan.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("api/loan")
public class LoanController {

    final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoan(@PathVariable UUID id) {
        Loan loan = loanService.findById(id);
        return new ResponseEntity<>(loan, HttpStatus.OK);
    }
}
