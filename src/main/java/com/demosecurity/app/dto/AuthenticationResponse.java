package com.demosecurity.app.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AuthenticationResponse {
    private String accessToken;
    private LocalDateTime expiresAt;
}
