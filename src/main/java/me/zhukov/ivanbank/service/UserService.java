package me.zhukov.ivanbank.service;

import me.zhukov.ivanbank.controller.dto.RegistrationRequest;

public interface UserService {
    long registerUser(RegistrationRequest request);
}
