package me.zhukov.ivanbank.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhukov.ivanbank.exception.EntityNotFoundByIdException;
import me.zhukov.ivanbank.model.Account;
import me.zhukov.ivanbank.model.Bank;
import me.zhukov.ivanbank.model.BankUser;
import me.zhukov.ivanbank.repository.AccountRepository;
import me.zhukov.ivanbank.repository.BankRepository;
import me.zhukov.ivanbank.repository.UserRepository;
import me.zhukov.ivanbank.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final BankRepository bankRepository;

    public long addAccount(long userId, long bankId) {
        BankUser bankUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundByIdException(BankUser.class, userId));
        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new EntityNotFoundByIdException(Bank.class, userId));
        Account account = new Account();
        account.setOwner(bankUser);
        account.setBank(bank);
        return accountRepository.save(account).getId();
    }

    public List<Account> findAllByUsername(String username) {
        return accountRepository.findAllByUsername(username);
    }
}
