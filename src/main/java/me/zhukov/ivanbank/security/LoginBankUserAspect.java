package me.zhukov.ivanbank.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhukov.ivanbank.controller.dto.Authentication;
import me.zhukov.ivanbank.exception.AuthenticationException;
import me.zhukov.ivanbank.model.BankUser;
import me.zhukov.ivanbank.repository.UserRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LoginBankUserAspect {
    private final UserRepository userRepository;

    @Around("execution(* me.zhukov.ivanbank.controller.*.*(..)) && " +
                     "!execution(* me.zhukov.ivanbank.controller.UserController.registerUser(..))")
    public Object login(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();

        Authentication auth = null;
        for (Object arg : args) {
            if (arg instanceof Authentication) {
                auth = (Authentication) arg;
                break;
            }
        }

        if (auth != null) {
            final String authLogin = auth.login();
            userRepository.findByUsername(auth.login()).orElseThrow(() -> new AuthenticationException(authLogin));
        } else {
            throw new AuthenticationException();
        }
        return proceedingJoinPoint.proceed();
    }
}
