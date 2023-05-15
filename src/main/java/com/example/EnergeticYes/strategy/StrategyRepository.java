package com.example.EnergeticYes.strategy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StrategyRepository extends JpaRepository<Strategy, Long>{
    Optional<Strategy> findStrategyByName(String name);
}
