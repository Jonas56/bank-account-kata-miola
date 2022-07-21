package com.kata.account;

import com.kata.client.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountRepositoryTest {
    private AccountRepository underTest;

    @BeforeEach
    void setUp() {
        underTest = new AccountRepository();
    }

    @Test
    void shouldAddOperationToAccount() {
        // Given
        Account account = new Account("123456789", 20.0, new ArrayList<>(), new Client("John", "john", "123"));
        Operation operation = new Deposit(10.0);
        // When
        underTest.addOperation(account, operation);
        // Then
        assertEquals(1, account.getOperations().size());
    }


    @Test
    void shouldAddAmountToAccount() {
        // Given
        Account account = new Account("123456789", 20.0, new ArrayList<>(), new Client("John", "john", "123"));
        // When
        underTest.addAmount(account, 10.0);
        // Then
        assertEquals(30.0, account.getBalance());
    }

    @Test
    void shouldRetrieveAmountFromAccount() throws NotEnoughMoneyException {
        // Given
        Account account = new Account("123456789", 20.0, new ArrayList<>(), new Client("John", "john", "123"));
        // When
        underTest.retrieveAmount(account, 10.0);
        // Then
        assertEquals(10.0, account.getBalance());
    }

    @Test
    void shouldThrowNotEnoughMoneyExceptionWhenRetrievingAmountFromAccount() throws NotEnoughMoneyException {
        // Given
        Account account = new Account("123456789", 20.0, new ArrayList<>(), new Client("John", "john", "123"));
        // When
        assertThrows(NotEnoughMoneyException.class, () -> underTest.retrieveAmount(account, 30.0));
    }

}