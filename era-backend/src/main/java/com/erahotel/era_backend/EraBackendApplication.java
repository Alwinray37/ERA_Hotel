package com.erahotel.era_backend;

import com.erahotel.era_backend.entity.Admin;
import com.erahotel.era_backend.repository.AdminRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EraBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EraBackendApplication.class, args);
	}

	@Bean
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
		};
	}

}
