package com.kata.account;

import com.kata.client.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class AccountRepositoryTest {
    private AccountRepository underTest;

    @BeforeEach
    void setUp() {
        underTest = new AccountRepository();
    }

    @Test
    void itShouldAddOperationToAccount() {
        // Given
        Account account = new Account(20.0, new ArrayList<>(), new Client("John", "john", "123"));
        Operation operation = new Deposit(10.0);
        // When
        underTest.addOperation(account, operation);
        // Then
        assertThat(1).isEqualTo(account.getOperations().size());
    }


    @Test
    void itShouldAddAmountToAccount() {
        // Given
        Account account = new Account(20.0, new ArrayList<>(), new Client("John", "john", "123"));
        // When
        underTest.addAmount(account, 10.0);
        // Then
        assertThat(30.0).isEqualTo(account.getBalance());
    }

    @Test
    void itShouldRetrieveAmountFromAccount() throws NotEnoughMoneyException {
        // Given
        Account account = new Account(20.0, new ArrayList<>(), new Client("John", "john", "123"));
        // When
        underTest.retrieveAmount(account, 10.0);
        // Then
        assertThat(10.0).isEqualTo(account.getBalance());
    }

    @Test
    void itShouldThrowNotEnoughMoneyExceptionWhenRetrievingAmountFromAccount() throws NotEnoughMoneyException {
        // Given
        Account account = new Account(20.0, new ArrayList<>(), new Client("John", "john", "123"));
        // When
        assertThatThrownBy(() -> underTest.retrieveAmount(account, 30.0))
                .isInstanceOf(NotEnoughMoneyException.class)
                .hasMessage("Not enough money");
    }

}