package me.zhukov.ivanbank.repository;

import me.zhukov.ivanbank.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, Long> {
    Optional<Bank> findByName(String name);
}
