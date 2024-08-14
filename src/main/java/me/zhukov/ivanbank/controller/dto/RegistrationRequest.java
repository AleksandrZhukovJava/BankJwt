package me.zhukov.ivanbank.controller.dto;

public record RegistrationRequest(String login, String password, String confirmPassword) {
}
