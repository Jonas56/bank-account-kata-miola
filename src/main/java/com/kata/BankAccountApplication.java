package com.kata;

import com.kata.account.*;
import com.kata.client.Client;

import java.util.ArrayList;

public class BankAccountApplication {

    public static void main(String[] args) {
        AccountRepository accountRepository = new AccountRepository();
        AccountService accountService = new AccountServiceImp(accountRepository);

        /**
         * Account 1 :
         */
        System.out.println("Account 1");
        Client client = new Client("John", "Doe", "password");
        Account account = new Account("123456789", 20.0, new ArrayList<>(), client);

        accountService.deposit(account, 10.0);
        try {
            accountService.withdraw(account, 5.0);
        } catch (NotEnoughMoneyException e) {
            System.out.println(e.getMessage());
        }
        accountService.printAccountHistory(account);
        /**
         * Account 2
         */
        System.out.println("-----------------");
        System.out.println("Account 2");
        Client client2 = new Client("Jonas", "Tesla", "password");
        Account account2 = new Account("987654321", 0.0, new ArrayList<>(), client2);
        try {
            accountService.withdraw(account2, 5.0);
        } catch (NotEnoughMoneyException e) {
            System.out.println(e.getMessage());
        }
        accountService.deposit(account2, 10.0);
        accountService.printAccountHistory(account2);
        System.out.println("-----------------");
    }
}
