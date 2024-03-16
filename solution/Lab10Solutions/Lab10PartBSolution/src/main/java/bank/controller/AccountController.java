package bank.controller;

import bank.service.AccountResponse;
import bank.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class AccountController {

    @Autowired
    AccountServiceImpl accountService;

    @PostMapping("/createAccount")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> createAccount(@RequestParam(value= "accountNumber") long accountNumber,
                                           @RequestParam(value= "customerName") String customerName) {
        AccountResponse accountDto = accountService.createAccount(accountNumber, customerName);
        return new ResponseEntity<AccountResponse>(accountDto, HttpStatus.OK);
    }

    @PostMapping("/accounts")
    @PreAuthorize("hasRole('ROLE_USER')")
    public AccountResponse accountOperation (
            @RequestParam(value= "accountNumber")long accountNumber,
            @RequestParam(value= "amount")double amount,
            @RequestParam(value= "operation") String operation,
            @RequestParam(value= "toAccountNumber")long toAccountNumber,
            @RequestParam(value= "description")String description) {
        if (operation. equals("deposit")) accountService.deposit(accountNumber, amount );
        if (operation. equals("depositEuros")) accountService.depositEuros(accountNumber, amount );
        if (operation. equals("withdraw")) accountService.withdraw(accountNumber, amount );
        if (operation. equals("withdrawEuros")) accountService.withdrawEuros(accountNumber, amount );
        if (operation. equals("transferFunds")) accountService.transferFunds(accountNumber, toAccountNumber, amount, description);
        return accountService.getAccount(accountNumber);
    }

    @GetMapping ("/accounts/{accountNumber}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAccount(@PathVariable("accountNumber") long accountNumber){
        AccountResponse accountDto = accountService.getAccount(accountNumber);
        return new ResponseEntity<AccountResponse>(accountDto, HttpStatus.OK);
    }

    @GetMapping ("/accounts")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllAccounts() {
        Collection<AccountResponse>  accountList = accountService.getAllAccounts();
        Accounts accounts = new Accounts();
        accounts.setAccountList(accountList);
        return new ResponseEntity<Accounts>(accounts, HttpStatus.OK);
    }

}
