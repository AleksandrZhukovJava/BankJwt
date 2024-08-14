package me.zhukov.ivanbank.exception;

import lombok.Getter;

@Getter
public class PasswordConfirmationException extends BankException {
    private final String password;
    private final String confirmPassword;

    public PasswordConfirmationException(String password, String confirmPassword) {
        super("Password - [%s], not equals confirmed password - [%s]".formatted(password, confirmPassword));
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
