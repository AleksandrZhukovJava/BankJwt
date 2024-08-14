package me.zhukov.ivanbank.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhukov.ivanbank.controller.dto.RegistrationRequest;
import me.zhukov.ivanbank.model.BankUser;
import me.zhukov.ivanbank.repository.UserRepository;
import me.zhukov.ivanbank.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<String> registerUser(RegistrationRequest request) {
        if (userRepository.findByUsername(request.login()).isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken.");
        }

        BankUser user = new BankUser(request.login(), passwordEncoder.encode(request.password()));
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully.");
    }
}
