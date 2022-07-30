package com.kata.account;

import com.kata.client.Client;

import java.util.List;
import java.util.UUID;

public class Account {
    private String accountNumber;
    private double balance;
    private List<Operation> operations;
    private Client client;


    public Account(double balance, List<Operation> operations, Client client) {
        this.accountNumber = UUID.randomUUID().toString();
        this.balance = balance;
        this.operations = operations;
        this.client = client;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public Client getClient() {
        return client;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", operations=" + operations +
                '}';
    }
}
