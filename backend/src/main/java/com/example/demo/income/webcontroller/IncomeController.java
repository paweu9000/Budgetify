package com.example.demo.income.webcontroller;

import com.example.demo.income.model.Income;
import com.example.demo.income.service.IncomeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("api/income")
public class IncomeController {

    final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Income> getIncome(@PathVariable UUID id) {
        Income income = incomeService.findById(id);
        return new ResponseEntity<>(income, HttpStatus.OK);
    }
}
