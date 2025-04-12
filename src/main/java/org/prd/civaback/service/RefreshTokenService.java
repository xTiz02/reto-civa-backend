package org.prd.civaback.service;

import org.prd.civaback.persistence.dto.AuthResponse;
import org.prd.civaback.persistence.entity.RefreshTokenEntity;
import org.prd.civaback.persistence.entity.UserEntity;

public interface RefreshTokenService {
    String createRefreshToken(UserEntity user);
    AuthResponse createNewJwtToken(String  rfToken);
    void deleteRefreshToken(String rf);
    RefreshTokenEntity updateRefreshToken(String username);
    boolean userHasRefreshToken(String username);
}