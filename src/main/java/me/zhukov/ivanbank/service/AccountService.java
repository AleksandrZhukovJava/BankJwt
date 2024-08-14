package me.zhukov.ivanbank.service;

import me.zhukov.ivanbank.model.Account;

import java.util.List;

public interface AccountService {
    long addAccount(long userId, long bankId);
    List<Account> findAllByUsername(String username);
}
