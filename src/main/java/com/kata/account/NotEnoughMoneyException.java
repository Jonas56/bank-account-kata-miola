package com.kata.account;

public class NotEnoughMoneyException extends Exception {

    private final String message;

    public NotEnoughMoneyException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}