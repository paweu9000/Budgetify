package com.example.demo.user.model;

import com.example.demo.income.model.Income;
import com.example.demo.loan.model.Loan;
import com.example.demo.savings.model.Savings;
import com.example.demo.spendings.model.Spendings;
import com.example.demo.transaction.model.Transaction;
import com.example.demo.user.roles.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private UUID id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private double balance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "income_id", referencedColumnName = "id")
    private Income income;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loan_id", referencedColumnName = "id")
    private Loan loan;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "savings_id", referencedColumnName = "id")
    private Savings savings;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "spendings_id", referencedColumnName = "id")
    private Spendings spendings;

    @OneToMany(mappedBy = "user")
    private Set<Transaction> transactions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String login, String username, String email, String password) {
        this.login = login;
        this.username = username;
        this.email = email;
        this.password = password;
        this.balance = 0.00;
        this.transactions = new HashSet<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public Savings getSavings() {
        return savings;
    }

    public void setSavings(Savings savings) {
        this.savings = savings;
    }

    public Spendings getSpendings() {
        return spendings;
    }

    public void setSpendings(Spendings spendings) {
        this.spendings = spendings;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }
}
