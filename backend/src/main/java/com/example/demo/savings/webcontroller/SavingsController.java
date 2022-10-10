package com.example.demo.savings.webcontroller;

import com.example.demo.savings.model.Savings;
import com.example.demo.savings.service.SavingsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/api/savings")
public class SavingsController {

    final SavingsService savingsService;

    public SavingsController(SavingsService savingsService) {
        this.savingsService = savingsService;
    }

    public ResponseEntity<Savings> getSavings(UUID id) {
        Savings savings = savingsService.findById(id);
        return new ResponseEntity<>(savings, HttpStatus.OK);
    }
}
