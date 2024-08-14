package me.zhukov.ivanbank.repository;

import me.zhukov.ivanbank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("select a from Account a where a.owner.username = :username")
    List<Account> findAllByUsername(String username);
}
