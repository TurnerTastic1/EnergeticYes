package com.example.EnergeticYes.trader;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TraderRepository extends JpaRepository<Trader, Long> {
    Optional<Trader> findTraderByEmail(String email);
}
