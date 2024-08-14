package me.zhukov.ivanbank.controller;

import lombok.RequiredArgsConstructor;
import me.zhukov.ivanbank.controller.dto.Authentication;
import me.zhukov.ivanbank.service.BankService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
@RequiredArgsConstructor
public class BankController {
    private final BankService bankService;

    @PostMapping("/add/{name}")
    public long addBank(@PathVariable("name") String name,
                        @RequestBody Authentication authentication) {
        return bankService.addBank(name);
    }
}
