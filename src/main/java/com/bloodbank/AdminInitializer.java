package com.bloodbank;

import com.bloodbank.model.User;
import com.bloodbank.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminInitializer {

    @Bean
    CommandLineRunner createAdmin(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            if (!userRepository.existsByEmail("admin@bloodbank.com")) {

                User admin = User.builder()
                        .name("Blood Bank Admin")
                        .email("admin@bloodbank.com")
                        .password(passwordEncoder.encode("Admin@123"))
                        .role(User.Role.ADMIN)
                        .build();

                userRepository.save(admin);

                System.out.println("=================================");
                System.out.println("ADMIN ACCOUNT CREATED");
                System.out.println("Email: admin@bloodbank.com");
                System.out.println("Password: Admin@123");
                System.out.println("=================================");

            } else {
                System.out.println("Admin account already exists.");
            }
        };
    }
}