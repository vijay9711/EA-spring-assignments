package bank.dto;

import java.util.List;

public class AccountDTO {
    private long accountNumber;
    private double balance;
    private String customerName;
    private List<AccountEntryDTO> entryList;

    public AccountDTO(long accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    public AccountDTO() {
    }


    // add getter and setter methods
    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<AccountEntryDTO> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<AccountEntryDTO> entryList) {
        this.entryList = entryList;
    }
}