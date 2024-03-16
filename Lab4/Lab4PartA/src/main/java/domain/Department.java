package domain;
// Create a Bidirectional OneToMany association between Department and Employee.

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    // Bidirectional OneToMany association between Department and Employee

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Employee> employees = new ArrayList<Employee>();

    public Department(String name) {
        this.name = name;
    }

    public Department() {

    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.setDepartment(this);
    }

    // All Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    // Override toString
    @Override
    public String toString() {
        return "Department --> " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employees=" + employees;
    }

}
