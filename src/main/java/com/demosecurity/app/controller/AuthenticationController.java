package com.demosecurity.app.controller;

import com.demosecurity.app.enums.Role;
import com.demosecurity.app.model.User;
import com.demosecurity.app.repo.UserRepo;
import com.demosecurity.app.service.AuthenticationService;
import com.demosecurity.app.dto.AuthenticationRequest;
import com.demosecurity.app.dto.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public AuthenticationResponse doLogin(@RequestBody AuthenticationRequest request){
        return authenticationService.doLogin(request);
    }

    @PostMapping("/signup")
    public void doSignup(@RequestBody AuthenticationRequest request){
        User admin = User.builder().name("Admin")
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.ADMIN)
                .build();

        User save = userRepo.save(admin);
        log.info(save.toString());
    }

}
