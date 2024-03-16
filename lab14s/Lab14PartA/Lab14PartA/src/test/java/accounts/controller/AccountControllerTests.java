package accounts.controller;

import accounts.service.AccountResponse;
import accounts.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTests {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @Test
    public void testGetAccount_AccountExists_ReturnsAccountResponse() {
        // Mocking
        String accountNumber = "123456";
        AccountResponse expectedAccount = new AccountResponse(accountNumber, 100.0, "John Doe");
        when(accountService.getAccount(accountNumber)).thenReturn(expectedAccount);

        // Test
        ResponseEntity<?> response = accountController.getAccount(accountNumber);

        // Verification
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedAccount, response.getBody());
    }

    @Test
    public void testGetAccount_AccountNotExists_ReturnsNotFoundResponse() {
        // Mocking
        String accountNumber = "123456";
        when(accountService.getAccount(accountNumber)).thenReturn(null);

        // Test
        ResponseEntity<?> response = accountController.getAccount(accountNumber);

        // Verification
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Account with account number " + accountNumber + " is not available", response.getBody());
    }

    @Test
    public void testCreateAccount_ValidData_ReturnsCreatedResponse() {
        // Mocking
        String accountNumber = "123456";
        String amount = "100.0";
        String accountHolder = "Dip";

        // Test
        ResponseEntity<?> response = accountController.createAccount(accountNumber, amount, accountHolder);

        // Verification
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Account with account number " + accountNumber + " is created", response.getBody());
        verify(accountService).createAccount(accountNumber, Double.parseDouble(amount), accountHolder);
    }
}
