package domain;

import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    private String studentId;

    private String firstName;
    private String lastName;

    // Constructors, getters, setters, etc.

    public Student() {
    }

    public Student(String studentId, String firstName, String lastName) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters and setters
    public String getStudentId() {
        return studentId;
    }

    // toString method for better representation
    @Override
    public String toString() {
        return "Student ---> " +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName;
    }
}

