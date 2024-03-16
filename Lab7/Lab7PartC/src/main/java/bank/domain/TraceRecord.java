package bank.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class TraceRecord {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime timestamp;
    private String result;

    public TraceRecord() {
        this.timestamp = LocalDateTime.now();
    }

    public TraceRecord(String result) {
        this();
        this.result = result;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "TraceRecord{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", result='" + result + '\'' +
                '}';
    }
}
