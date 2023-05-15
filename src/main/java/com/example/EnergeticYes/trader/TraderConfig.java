package com.example.EnergeticYes.trader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TraderConfig {

    @Bean
    CommandLineRunner commandLineRunner(TraderRepository repository) {
        return args -> {
            Trader john = new Trader(
                    1L,
                    "John",
                    "John@gmail.com",
                    "password"
            );

            Trader alex = new Trader(
                    2L,
                    "Alex",
                    "Alex@gmail.com",
                    "password"
            );

            repository.saveAll(
                    List.of(john, alex)
            );
        };
    }
}
