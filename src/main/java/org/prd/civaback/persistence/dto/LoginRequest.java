package org.prd.civaback.persistence.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(
        @NotNull @NotEmpty String username,
        @NotNull @NotEmpty String password
) {
}