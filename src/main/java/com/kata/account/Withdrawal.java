package com.kata.account;

import java.time.LocalDate;

public class Withdrawal extends Operation {
    public Withdrawal(double amount) {
        super(amount, LocalDate.now());
    }

    @Override
    public String toString() {
        return "Withdrawal{" +
                "-" + this.getAmount() +
                ", date=" + this.getDate() +
                '}';
    }
}
