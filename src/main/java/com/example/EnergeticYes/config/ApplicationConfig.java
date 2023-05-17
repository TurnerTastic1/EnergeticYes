package com.example.EnergeticYes.config;

import com.example.EnergeticYes.strategy.Strategy;
import com.example.EnergeticYes.strategy.StrategyRepository;
import com.example.EnergeticYes.trader.Trader;
import com.example.EnergeticYes.trader.TraderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ApplicationConfig {
    @Bean
    CommandLineRunner commandLineRunner(TraderRepository TraderRepo, StrategyRepository StrategyRepo) {
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

            TraderRepo.saveAll(
                    List.of(john, alex)
            );

            Strategy strategy1 = new Strategy(
                    1L,
                    "Strategy1",
                    "This is strategy 1",
                    "This is the first strategy"
            );

            Strategy strategy2 = new Strategy(
                    2L,
                    "Strategy2",
                    "This is strategy 2",
                    "This is the second strategy"
            );

            StrategyRepo.saveAll(
                    List.of(strategy1, strategy2)
            );
        };
    }
}
