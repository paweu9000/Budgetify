package com.example.demo.spendings.webcontroller;

import com.example.demo.spendings.model.Spendings;
import com.example.demo.spendings.service.SpendingsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("api/spendings")
public class SpendingsController {

    final SpendingsService spendingsService;

    public SpendingsController(SpendingsService spendingsService) {
        this.spendingsService = spendingsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Spendings> getSpendings(@PathVariable UUID id) {
        Spendings spendings = spendingsService.findById(id);
        return new ResponseEntity<>(spendings, HttpStatus.OK);
    }
}
