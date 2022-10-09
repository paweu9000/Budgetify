package com.example.demo.history.service;

import com.example.demo.history.model.History;
import com.example.demo.history.repository.HistoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class HistoryService {
    final HistoryRepository repository;

    public HistoryService(HistoryRepository repository) {
        this.repository = repository;
    }

    public History findById(UUID id) {
        return unwrapHistory(id);
    }

    public History unwrapHistory(UUID id) {
        Optional<History> history = this.repository.findById(id);
        if (history.isPresent()) {
            return history.get();
        } else {
            throw new RuntimeException("History Object with id: " + id.toString() + " does not exist!");
        }
    }
}
