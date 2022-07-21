package com.kata.account;

public interface AccountService {

    void deposit(Account account, double amount);

    void withdraw(Account account, double amount) throws NotEnoughMoneyException;

    void printAccountHistory(Account account);
}
