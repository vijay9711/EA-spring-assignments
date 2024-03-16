package accounts.service;

import accounts.domain.Account;
import accounts.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@RunWith(SpringRunner.class)
public class AccountServiceTests {
    @TestConfiguration
    static class AccountServiceTestContextConfiguration {
        @Bean
        public AccountService accountService() {
            return new AccountService();
        }
    }

    @Autowired
    AccountService accountService;
    @MockBean
    AccountRepository accountRepository;

    @Before
    public void setUp() {
        String accountNumber = "123456";
        Account account = new Account(accountNumber, 99.99d, "Dip");
        Optional<Account> accountOptional = Optional.of(account);
        Mockito.when(accountRepository.findById(accountNumber)).thenReturn(accountOptional);
    }

    @Test
    public void whenAccountNumberValidThenAccountShouldBeFound() {
        String accountNumber = "123456";
        AccountResponse accountResponse = accountService.getAccount(accountNumber);
        assertThat(accountResponse.getAccountNumber()).isEqualTo(accountNumber);
    }

}
