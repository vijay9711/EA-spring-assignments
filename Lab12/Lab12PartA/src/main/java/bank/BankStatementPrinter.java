package bank;

import bank.service.AccountEntryResponse;
import bank.service.AccountResponse;
import bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class BankStatementPrinter {

    @Autowired
    private AccountService accountService;

    @Scheduled(fixedRate = 20000)
    public void printBankStatement() {
        System.out.println("Bank Statement:");

        Collection<AccountResponse> accounts = accountService.getAllAccounts();
        for (AccountResponse account : accounts) {
            System.out.println("Account Number: " + account.getAccountnumber());
            System.out.println("Customer Name: " + account.getCustomer().getName());
            System.out.println("Balance: " + account.getBalance());
            System.out.println("------------- Customer Entry ----------------");
            Collection<AccountEntryResponse> entries = account.getEntryList();
            for (AccountEntryResponse entry : entries) {
                System.out.println("Account: " + entry.getFromAccountNumber());
                System.out.println("Name: " + entry.getFromPersonName());
            }
            System.out.println("-----------------------------");
        }

        System.out.println("End of Bank Statement");
    }
}
