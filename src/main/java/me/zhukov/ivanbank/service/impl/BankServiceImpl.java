package me.zhukov.ivanbank.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhukov.ivanbank.model.Bank;
import me.zhukov.ivanbank.repository.BankRepository;
import me.zhukov.ivanbank.service.BankService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;

    public long addBank(String name) {
        log.info("[{}] bank was added", name);
        return bankRepository.save(new Bank(name)).getId();
    }
}
