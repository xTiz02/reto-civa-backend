package org.prd.civaback.persistence.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RefreshTokenRequest(
        @NotNull @NotEmpty String refreshToken
) {
}