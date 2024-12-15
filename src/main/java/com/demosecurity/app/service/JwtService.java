package com.demosecurity.app.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {
    private String signingKey;


    @PostConstruct
    public void init() {
        // Generate a 256-bit (32-byte) key
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[32]; // 256 bits = 32 bytes
        secureRandom.nextBytes(keyBytes);

        // Encode the key as a Base64 string
        this.signingKey = Base64.getEncoder().encodeToString(keyBytes);
        log.info("JWT Signing Key: {}", signingKey);
    }


    public String generateToken(Map<String, Object> claims , UserDetails userDetails) {
       return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSignInKey())
               .compact();
    }


    private Key getSignInKey(){
        byte[] decoded = Decoders.BASE64.decode(signingKey);
        return Keys.hmacShaKeyFor(decoded);
    }

}
