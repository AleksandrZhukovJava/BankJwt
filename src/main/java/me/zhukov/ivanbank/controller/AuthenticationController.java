package me.zhukov.ivanbank.controller;

import lombok.RequiredArgsConstructor;
import me.zhukov.ivanbank.controller.dto.AuthenticationRequest;
import me.zhukov.ivanbank.controller.dto.RegistrationRequest;
import me.zhukov.ivanbank.exception.AuthenticationException;
import me.zhukov.ivanbank.security.JwtUtil;
import me.zhukov.ivanbank.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/create")
    public String createToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.login(),
                            authenticationRequest.password()));
            if (authentication.isAuthenticated()) {
                return jwtUtil.generateToken((UserDetails) authentication.getPrincipal());
            } else {
                throw new Exception("Invalid credentials");
            }
        } catch (AuthenticationException e) {
            throw new Exception("Invalid credentials", e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest request) {
        return userService.registerUser(request);
    }
}
