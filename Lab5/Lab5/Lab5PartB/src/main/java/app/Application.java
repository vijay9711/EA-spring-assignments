package app;

import domain.Appointment;
import domain.Doctor;
import domain.Patient;
import domain.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.AppointmentRepository;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class Application implements CommandLineRunner{
	@Autowired
	AppointmentRepository appointmentRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Doctor doctor1 = new Doctor("Nurogoon", "Mathru", "Boodham");
		Doctor doctor2 = new Doctor("ENT", "sandar", "bey");

		Payment payment1 = new Payment("1-10-2023", 12.50);
		Payment payment2 = new Payment("1-11-2023", 45.00);

		Patient patient1 = new Patient("Jerry Lewis", "34 4th avenue",
				"13221", "New York");
		Patient patient2 = new Patient("Frank Moore", "34 Mainstret",
				"13221", "New York");

		Appointment appointment1 = new Appointment("1-11-2023", patient1,
				payment1, doctor1);
		Appointment appointment2 = new Appointment("1-12-2023", patient2,
				payment2, doctor2);

		appointmentRepository.save(appointment1);
		appointmentRepository.save(appointment2);

		appointmentRepository.findAll().forEach(System.out::println);


	}

}
