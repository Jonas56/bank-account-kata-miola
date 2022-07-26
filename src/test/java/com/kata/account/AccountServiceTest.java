package com.kata.account;

import com.kata.client.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;
    private AutoCloseable autoCloseable;
    private AccountService underTest;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new AccountServiceImp(accountRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void itShouldSuccessfullyMakeDeposit() {
        // Given
        Account account = new Account(20.0, new ArrayList<>(), new Client("John", "john", "123"));
        // When
        underTest.deposit(account, 50.0);
        // Then
        ArgumentCaptor<Account> argumentCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountRepository).addAmount(argumentCaptor.capture(), eq(50.0));
        Account capturedAccount = argumentCaptor.getValue();
        assertEquals(account, capturedAccount);
    }

    @Test
    void itShouldSuccessfullyMakeWithdrawal() throws NotEnoughMoneyException {
        // Given
        Client client = new Client("John", "john", "123");
        Account account = new Account(20.0, new ArrayList<>(), client);
        // When
        underTest.withdraw(account, 10.0);
        // Then
        ArgumentCaptor<Account> argumentCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountRepository).retrieveAmount(argumentCaptor.capture(), eq(10.0));
        Account capturedAccount = argumentCaptor.getValue();
        assertEquals(account, capturedAccount);
    }

    @Test
    public void itShouldNotMakeAWithdrawalWithInsufficientFunds() throws NotEnoughMoneyException {
        // Given
        Client client = new Client("John", "john", "123");
        Account account = new Account(20.0, new ArrayList<>(), client);
        given(accountRepository.retrieveAmount(account, 30.0)).willThrow(new NotEnoughMoneyException("Not enough money"));

        // When
        // Then
        assertThatThrownBy(() -> underTest.withdraw(account, 30.0))
                .isInstanceOf(NotEnoughMoneyException.class)
                .hasMessageContaining("Not enough money");
    }
}
