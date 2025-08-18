package com.erahotel.era_backend;

import com.erahotel.era_backend.entity.Admin;
import com.erahotel.era_backend.repository.AdminRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.erahotel.era_backend.entity.Room;
import com.erahotel.era_backend.repository.RoomRepository;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.util.ArrayList;


/**
 * Main application class for the EraHotel backend.
 * <p>
 * Initializes the Spring Boot application and seeds default admin users if they do not exist.
 * </p>
 *
 * @author alwin roble
 */
@SpringBootApplication
public class EraBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EraBackendApplication.class, args);
	}

	/**
	 * Initializes default admin users in the database if they do not already exist.
	 *
	 * @param adminRepository the repository for Admin entities
	 * @return a {@link CommandLineRunner} that seeds the admin data
	 */
	@Bean
	@Profile("!test")
	public CommandLineRunner initAdmin(AdminRepository adminRepository) {
		return args -> {
			String defaultAdminEmail = "alwin@email.com";
			String farshadEmail = "farshad@email.com";

			if (adminRepository.findByEmail(defaultAdminEmail).isEmpty()) {
				Admin admin = new Admin();
				admin.setEmail(defaultAdminEmail);
				admin.setName("Alwin Roble");
				admin.setPassword("password");
				adminRepository.save(admin);
			}
			if (adminRepository.findByEmail(farshadEmail).isEmpty()) {
				Admin admin = new Admin();
				admin.setEmail(farshadEmail);
				admin.setName("Farshad Azam");
				admin.setPassword("password");
				adminRepository.save(admin);
			}
			if (adminRepository.findByEmail("ryley@email.com").isEmpty()) {
				Admin admin = new Admin();
				admin.setEmail("ryley@email.com");
				admin.setName("Ryley Panopio");
				admin.setPassword("password");
				adminRepository.save(admin);
			}
			if (adminRepository.findByEmail("ed@email.com").isEmpty()) {
				Admin admin = new Admin();
				admin.setEmail("ed@email.com");
				admin.setName("Ed Margarian");
				admin.setPassword("password");
				adminRepository.save(admin);
			}
		};
	}

	/**
	 * Initializes Room instances in the database
	 */
	@Bean
	@Profile("!test")
	public CommandLineRunner initRooms(RoomRepository roomRepository) {
		return args -> {
			Room[] starterRooms = new Room[] {
					new Room(
							null, // roomId will be auto-generated
							"101",
							"Modern single room with city view, featuring a plush queen bed, workspace, and all essential amenities.",
							new BigDecimal("110.99"),
							"Single",
							"../assets/Images/Room 101.jpg",
							new ArrayList<>()
					),
					new Room(
							null,
							"202",
							"Spacious double room with two queen beds, ideal for families or groups, and a relaxing lounge area.",
							new BigDecimal("220.00"),
							"Double",
							"../assets/Images/Room 202.jpg",
							new ArrayList<>()
					),
					new Room(
							null,
							"303",
							"Premium suite with separate living area, king bed, luxury bath, and panoramic city views.",
							new BigDecimal("350.00"),
							"Suite",
							"../assets/Images/Room 303.jpg",
							new ArrayList<>()
					),
					new Room(
							null,
							"309",
							"Elegant king room with a comfortable king-size bed, modern decor, and a quiet ambiance.",
							new BigDecimal("180.00"),
							"King",
							"../assets/Images/Room 309.jpg",
							new ArrayList<>()
					),
					new Room(
							null,
							"310",
							"Double room with two queen beds, city view, and contemporary furnishings for a restful stay.",
							new BigDecimal("220.00"),
							"Double",
							"../assets/Images/Room 310.jpg",
							new ArrayList<>()
					)
			};

			for (Room room : starterRooms) {
				if (roomRepository.findByRoomNumber(room.getRoomNumber()).isEmpty()) {
					roomRepository.save(room);
				}
			}
		};
	}


}