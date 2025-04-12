package org.prd.civaback.service;

import org.prd.civaback.persistence.dto.AuthResponse;
import org.prd.civaback.persistence.dto.LoginRequest;

public interface AuthService {
    AuthResponse login(LoginRequest authRequest);
    void logout(String rf);
}