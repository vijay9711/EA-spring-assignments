package bank.event;

import bank.domain.AccountTraceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import bank.service.AccountResponse;
import org.springframework.stereotype.Service;
import bank.repository.AccountTraceRecordRepository;

import java.util.Date;

@Service
public class AccountChangeEventListener {
    @Autowired
    AccountTraceRecordRepository traceRecordRepository;

    @EventListener
    public void onEvent(AccountChangeEvent event) {
        System.out.println("Received account change event: " + event.getMessage());

        // Access the AccountResponse object from the event
        AccountResponse account = event.getAccount();

        // Print account details
        System.out.println("================= Account details =================");
        System.out.println("Account Number: " + account.getAccountnumber());
        System.out.println("Customer Name: " + account.getCustomer().getName());
        System.out.println("Balance: " + account.getBalance());
        System.out.println("==================================================");

        // Create trace record to database
        AccountTraceRecord traceRecord = new AccountTraceRecord();
        traceRecord.setTimestamp(new Date());
        traceRecord.setAccountNumber(account.getAccountnumber());
        traceRecord.setOperation(event.getMessage());
        traceRecord.setAmount(event.getAmount()); // Assuming balance represents the amount for certain operations
        traceRecordRepository.save(traceRecord);
    }
}
