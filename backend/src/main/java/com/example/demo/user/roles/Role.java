package com.example.demo.user.roles;

import com.example.demo.user.model.User;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private UUID id;

    @Column(length = 60)
    private String name;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
