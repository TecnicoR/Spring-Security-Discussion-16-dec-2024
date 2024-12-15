package com.demosecurity.app.service;


import com.demosecurity.app.dto.AuthenticationRequest;
import com.demosecurity.app.dto.AuthenticationResponse;
import com.demosecurity.app.model.User;
import com.demosecurity.app.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;
    private final JwtService jwtService;

    public AuthenticationResponse doLogin(AuthenticationRequest request) {
        Authentication authenticated = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepo.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Map<String , Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("user", user.getId());
        String token = jwtService.generateToken(objectObjectHashMap,user);
        return AuthenticationResponse.builder()
                .accessToken(token)
                .build();
    }
}
