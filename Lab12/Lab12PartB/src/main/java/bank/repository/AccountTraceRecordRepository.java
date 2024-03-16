package bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import bank.domain.AccountTraceRecord;

public interface AccountTraceRecordRepository extends JpaRepository<AccountTraceRecord, Long> {

}
