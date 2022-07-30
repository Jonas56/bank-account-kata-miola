package com.kata.account;

public class AccountRepository {

    public void addOperation(Account account, Operation operation) {
        account.getOperations().add(operation);
    }

    public void addAmount(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
    }

    public void retrieveAmount(Account account, double amount) throws NotEnoughMoneyException {
        if (account.getBalance() - amount < 0) {
            throw new NotEnoughMoneyException("Not enough money");
        }
        account.setBalance(account.getBalance() - amount);
    }

    public String getAccountHistory(Account account) {
        return account.getOperations().stream()
                .map(Operation::toString)
                .reduce("", (a, b) -> a + "\n" + b);
    }
}
