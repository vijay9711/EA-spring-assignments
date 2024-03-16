package bank.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class AccountTraceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date timestamp;
    private long accountNumber;
    private String operation;
    private double amount;

    // Constructors, getters, and setters
    public AccountTraceRecord() {
    }
    public AccountTraceRecord(Date timestamp, long accountNumber, String operation, double amount) {
        this.timestamp = timestamp;
        this.accountNumber = accountNumber;
        this.operation = operation;
        this.amount = amount;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    public long getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getOperation() {
        return operation;
    }
    public void setOperation(String operation) {
        this.operation = operation;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    @Override
    public String toString() {
        return "AccountTraceRecord{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", accountNumber=" + accountNumber +
                ", operation='" + operation + '\'' +
                ", amount=" + amount +
                '}';
    }
}
