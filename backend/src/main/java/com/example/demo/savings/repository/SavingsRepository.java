package com.example.demo.savings.repository;

import com.example.demo.savings.model.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SavingsRepository extends JpaRepository<Savings, UUID> {
}
