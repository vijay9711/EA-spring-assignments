package bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class BankStatementPrinter {

    @Autowired
    private AccountService accountService;

    @Scheduled(fixedRate = 20000) // Execute every 20 seconds
    public void printBankStatement() {
        System.out.println("Bank Statement:");

        Collection<AccountResponse> accounts = accountService.getAllAccounts();
        for (AccountResponse account : accounts) {
            System.out.println("Account Number: " + account.getAccountnumber());
            System.out.println("Customer Name: " + account.getCustomer().getName());
            System.out.println("Balance: " + account.getBalance());
            System.out.println("Recent Transactions:");

            Collection<AccountEntryResponse> entries = account.getEntryList();
            for (AccountEntryResponse entry : entries) {
                System.out.println("Date: " + entry.getDate());
                System.out.println("Amount: " + entry.getAmount());
                System.out.println("Description: " + entry.getDescription());
                System.out.println("From Account: " + entry.getFromAccountNumber());
                System.out.println("From Person: " + entry.getFromPersonName());
                System.out.println("-----------------------------");
            }
            System.out.println("-----------------------------");
        }

        System.out.println("End of Bank Statement");
    }
}
