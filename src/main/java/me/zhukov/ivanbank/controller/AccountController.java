package me.zhukov.ivanbank.controller;

import lombok.RequiredArgsConstructor;
import me.zhukov.ivanbank.model.Account;
import me.zhukov.ivanbank.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/add")
    public long addAccount(@RequestParam("userId") long userId,
                           @RequestParam("bankId") long bankId) {
        return accountService.addAccount(userId, bankId);
    }

    @PutMapping("/my")
    public List<Account> findAllAccountByCurrentUser() {
        return accountService.findAllByUsername("");
    }
}
