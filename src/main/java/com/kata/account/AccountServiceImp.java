package com.kata.account;

public class AccountServiceImp implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void deposit(Account account, double amount) {
        accountRepository.addAmount(account, amount);
        accountRepository.addOperation(account, new Deposit(amount));
    }

    @Override
    public void withdraw(Account account, double amount) throws NotEnoughMoneyException {
        accountRepository.retrieveAmount(account, amount);
        accountRepository.addOperation(account, new Withdrawal(amount));
    }

    @Override
    public void printAccountHistory(Account account) {
        System.out.println(accountRepository.getAccountHistory(account));
    }
}
