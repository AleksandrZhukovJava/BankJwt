package me.zhukov.ivanbank.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends BankException {
    private String login;

    public AuthenticationException(String login) {
        super("Login - [%s], not registered".formatted(login));
        this.login = login;
    }

    public AuthenticationException() {
        super("Can`t authenticate user without login");
    }
}
