package org.alphaspring.store.config;


import org.alphaspring.store.entity.User;
import org.alphaspring.store.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return args ->{
            if(userRepository.findByUsername("samaun").isEmpty()){
                User normalUser = new User("samaun",
                        passwordEncoder.encode("password123"),
                                Set.of("USER")
                        );

                userRepository.save(normalUser);
                System.out.println("Seeded default user: samaun");
            }

            if(userRepository.findByUsername("admin").isEmpty()){
                User adminUser = new User("admin",
                        passwordEncoder.encode("adminpass"),
                        Set.of("AMDIN"));

                userRepository.save(adminUser);
                System.out.println("Seeded default user: admin");
            }

        };
    }
}
