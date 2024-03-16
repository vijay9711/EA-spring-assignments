package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Employee {
    @Id
    private int employeeNumber;
    private String name;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee(int employeeNumber, String name) {
        this.employeeNumber = employeeNumber;
        this.name = name;
    }

    public Employee() {

    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    // All Getters and Setters
    public int getEmployeeNumber() {
        return employeeNumber;
    }
    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Department getDepartment() {
        return department;
    }

    // Override toString
    @Override
    public String toString() {
        return "Employee --> " +
                "employeeNumber=" + employeeNumber +
                ", name='" + name;
    }


}
