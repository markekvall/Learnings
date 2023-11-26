package com.example.booking;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);
	}

}

@Component
@Slf4j
class BookingCommandLineRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		for (Booking b : this.bookingRepository.findAll()) {
			log.info("Booking user {} with ID {}", b.getBookingName(), b.getId());
		}
	}

	@Autowired
	BookingRepository bookingRepository;
}

interface BookingRepository extends JpaRepository<Booking, Long> {

	Collection<Booking> findByBookingName(String bookingName);
}

@RestController
class BookingRestController {

	@RequestMapping("/bookings")
		Collection<Booking> bookings () {
			return this.bookingRepository.findAll();
		}

		@Autowired
		BookingRepository bookingRepository;
}

@Entity
class Booking {


	@Id @GeneratedValue
	private Long id;


	private String bookingName;
	public Booking(String bookingName) {
		super();
		this.bookingName = bookingName;
	}
	public Booking() {
	}

	public Long getId() {
		return id;
	}

	public String getBookingName() {
		return bookingName;
	}

}