package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.*;
import domain.*;

import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain")
public class Application implements CommandLineRunner{
	
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	PassengerRepository passengerRepository;
	@Autowired
	SchoolRepository schoolRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Department department = new Department("IT");
		Employee employee = new Employee(1, "Rim");
		department.addEmployee(employee);
		employee = new Employee(2, "Tom");
		department.addEmployee(employee);
		departmentRepository.save(department);
		System.out.println("Department Data");
		System.out.println("findAll");
		System.out.println("-------------------------------");
		for (Department dept : departmentRepository.findAll()) {
			System.out.println(dept);
		}
		Publisher publisher = new Publisher("Atom");
		Book book = new Book(12345,"Java5", "Rig");
		book.setPublisher(publisher);

		System.out.println("publisher: " + book);
		bookRepository.save(book);
		System.out.println("Books");
		// fetch all books
		for (Book b : bookRepository.findAll()) {
			System.out.println(b);
		}

		// flight data
		Flight flight = new Flight("9989", "Las", "San", "2022-11-11");
		Passenger passenger = new Passenger("John");
		passenger.addFlight(flight);
		flight = new Flight("5434", "Mumbai", "Daka", "2021-12-10");
		passenger.addFlight(flight);
		flight = new Flight("2323", "Sedar", "Rajas", "2021-13-20");
		passenger.addFlight(flight);
		passengerRepository.save(passenger);


		System.out.println("Flight");
		for (Passenger p : passengerRepository.findAll()) {
			System.out.println(p);
		}
		School school = new School("Rockfort School");
		Student student = new Student("65748","Anam", "Andrew");
		school.addStudent(student);
		student = new Student("85673", "Tom", "Shelby");
		school.addStudent(student);
		schoolRepository.save(school);
		System.out.println("School");
		for (School s : schoolRepository.findAll()) {
			System.out.println(s);
		}





	}

}
