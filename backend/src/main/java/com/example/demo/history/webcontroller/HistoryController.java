package com.example.demo.history.webcontroller;

import com.example.demo.history.model.History;
import com.example.demo.history.service.HistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("api/history")
public class HistoryController {

    final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<History> getHistory(@PathVariable UUID id) {
        History history = historyService.findById(id);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

}
