package me.zhukov.ivanbank.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhukov.ivanbank.controller.dto.RegistrationRequest;
import me.zhukov.ivanbank.exception.PasswordConfirmationException;
import me.zhukov.ivanbank.model.BankUser;
import me.zhukov.ivanbank.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public long registerUser(RegistrationRequest request) {
        if (!request.password().equals(request.confirmPassword())) {
            throw new PasswordConfirmationException(request.password(), request.confirmPassword());
        }
        log.info("User with login {} was registered", request.login());
        return userRepository.save(new BankUser(request.login(), request.password())).getId();
    }
}
