package com.example.demo.history.service;

import com.example.demo.history.repository.HistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {
    final HistoryRepository repository;

    public HistoryService(HistoryRepository repository) {
        this.repository = repository;
    }
}
