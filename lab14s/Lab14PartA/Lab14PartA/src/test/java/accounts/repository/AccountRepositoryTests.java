package accounts.repository;


import accounts.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void whenFindByAccountHolder_thenReturnAccount() {
        // given
        Account account = new Account("123456", 100.0, "Dip");
        entityManager.persist(account);
        entityManager.flush();
        // when
        Optional<Account> found = accountRepository.findByAccountHolder(account.getAccountHolder());
        // then
        assertThat(found.get().getAccountHolder()).isEqualTo("Dip");
    }

    @Test
    public void whenFindByAccountNumber_thenReturnAccount() {
        // given
        Account account = new Account("987654", 100.0, "Dip");
        entityManager.persist(account);
        entityManager.flush();
        // when
        Optional<Account> found = accountRepository.findByAccountNumber(account.getAccountNumber());
        // then
        assertThat(found.get().getAccountNumber()).isEqualTo("987654");
    }

    @Test
    public void whenFindByAccountNumber_thenReturnBalance() {
        // given
        Account account = new Account("987654", 99.99, "Dip");
        entityManager.persist(account);
        entityManager.flush();
        // when
        Double balance = accountRepository.findBalanceByAccountNumber(account.getAccountNumber());
        // then
        assertThat(balance).isEqualTo(99.99d);
    }
}