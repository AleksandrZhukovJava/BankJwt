package me.zhukov.ivanbank.service;

import me.zhukov.ivanbank.controller.dto.RegistrationRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<String> registerUser(RegistrationRequest request);
}
