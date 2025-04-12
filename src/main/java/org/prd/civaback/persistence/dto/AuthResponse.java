package org.prd.civaback.persistence.dto;

public record AuthResponse(
        String accessToken,
        String refreshToken
) {
}