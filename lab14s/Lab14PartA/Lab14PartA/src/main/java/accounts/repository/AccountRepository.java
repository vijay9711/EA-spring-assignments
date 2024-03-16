package accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import accounts.domain.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>{
   Optional<Account> findByAccountHolder(String accountHolder);
   Optional<Account> findByAccountNumber(String accountNumber);
   @Query("Select a.balance from Account a where a.accountNumber=:accountNumber")
   Double findBalanceByAccountNumber(@Param("accountNumber") String accountNumber);

}
