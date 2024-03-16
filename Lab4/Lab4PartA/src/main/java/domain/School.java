package domain;

import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id")
    @MapKey(name = "studentId")
    private Map<String, Student> students = new HashMap<>();

    // Constructors, getters, setters, etc.

    public School() {
    }

    public School(String name) {
        this.name = name;
    }

    // Getters and setters

    // Add student to the map
    public void addStudent(Student student) {
        students.put(student.getStudentId(), student);
    }

    // Remove student from the map
    public void removeStudent(Student student) {
        students.remove(student.getStudentId());
    }

    // toString method for better representation
    @Override
    public String toString() {
        return "School --> " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students;
    }
}

