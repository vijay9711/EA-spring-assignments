package client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@Component
public class BankGateway {

    RestTemplate restTemplate = new RestTemplate();

    private String serverUrl = "http://localhost:8080/";

    public AccountResponse createAccount(long accountNumber, String customerName) {
        restTemplate.postForLocation(serverUrl+"/createAccount?accountNumber={accountNumber}&customerName={customerName}", AccountResponse.class,accountNumber, customerName);
        return getAccount(accountNumber);
    }


    public void deposit(long accountNumber, double amount) {
        restTemplate.postForLocation(serverUrl+"/accounts?accountNumber={accountNumber}&amount={amount}&operation={operation}&toAccountNumber=0&description=", AccountResponse.class,accountNumber, amount,"deposit");
    }

    public AccountResponse getAccount(long accountNumber) {
        AccountResponse accountResponse= restTemplate.getForObject(serverUrl+"accounts/{accountNumber}", AccountResponse.class, accountNumber);
        return accountResponse;
    }

    public Collection<AccountResponse> getAllAccounts() {
        Accounts accounts= restTemplate.getForObject(serverUrl+"accounts", Accounts.class);
        return accounts.getAccountList();
    }


    public void withdraw(long accountNumber, double amount) {
        restTemplate.postForLocation(serverUrl+"/accounts?accountNumber={accountNumber}&amount={amount}&operation={operation}&toAccountNumber=0&description=", AccountResponse.class,accountNumber, amount,"withdraw");
    }


    public void depositEuros(long accountNumber, double amount) {
        restTemplate.postForLocation(serverUrl+"/accounts?accountNumber={accountNumber}&amount={amount}&operation={operation}&toAccountNumber=0&description=", AccountResponse.class,accountNumber, amount,"depositEuros");
    }

    public void withdrawEuros(long accountNumber, double amount) {
        restTemplate.postForLocation(serverUrl+"/accounts?accountNumber={accountNumber}&amount={amount}&operation={operation}&toAccountNumber=0&description=", AccountResponse.class,accountNumber, amount,"withdrawEuros");
    }

    public void transferFunds(long fromAccountNumber, long toAccountNumber,
                              double amount, String description) {
        restTemplate.postForLocation(serverUrl+"/accounts?accountNumber={accountNumber}&amount={amount}&operation={operation}&toAccountNumber={toAccountNumber}&description={description}", AccountResponse.class,fromAccountNumber, amount,"transferFunds",toAccountNumber, description);
    }

}
