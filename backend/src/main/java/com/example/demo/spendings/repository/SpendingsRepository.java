package com.example.demo.spendings.repository;

import com.example.demo.spendings.model.Spendings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpendingsRepository extends JpaRepository<Spendings, UUID> {
}
