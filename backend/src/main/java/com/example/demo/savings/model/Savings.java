package com.example.demo.savings.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@Entity
@Table(name = "savings")
public class Savings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "id", columnDefinition = "VARCHAR(255)")
    private UUID id;

    @Column(nullable = false, name = "amount")
    private double amount;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
