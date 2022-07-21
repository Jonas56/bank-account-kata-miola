package com.kata.account;

import java.time.LocalDate;

public class Deposit extends Operation {

    public Deposit(double amount) {
        super(amount, LocalDate.now());
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "+" + this.getAmount() +
                ", date=" + this.getDate() +
                "}";
    }
}