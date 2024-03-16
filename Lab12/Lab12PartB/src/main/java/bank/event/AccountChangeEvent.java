package bank.event;

import bank.service.AccountResponse;

public class AccountChangeEvent {
    private String message;
    private AccountResponse account;
    private double amount;
    public AccountChangeEvent(String message, AccountResponse account, double amount) {
        this.message = message;
        this.account = account;
    }
    public String getMessage() {
        return message;
    }
    public AccountResponse getAccount() {
        return account;
    }
    public double getAmount() {
        return amount;
    }
}