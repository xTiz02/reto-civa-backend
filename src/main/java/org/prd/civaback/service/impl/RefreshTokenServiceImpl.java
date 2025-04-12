package org.prd.civaback.service.impl;

import org.prd.civaback.persistence.dto.AuthResponse;
import org.prd.civaback.persistence.entity.RefreshTokenEntity;
import org.prd.civaback.persistence.entity.UserEntity;
import org.prd.civaback.persistence.repository.UserRepository;
import org.prd.civaback.service.RefreshTokenService;
import org.prd.civaback.persistence.repository.TokenRepository;
import org.prd.civaback.service.jwt.JwtUtil;
import org.prd.civaback.util.EErrorType;
import org.prd.civaback.config.exception.ExpiredTokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final JwtUtil jwtUtil;
    @Value("${security.jwt.refresh-token-expiration-in-minutes}")
    private Long REFRESH_TOKEN_EXPIRATION_IN_MINUTES;

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    public RefreshTokenServiceImpl(TokenRepository tokenRepository, JwtUtil jwtUtil, UserRepository userRepository) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    @Transactional
    public String createRefreshToken(UserEntity user) {
        RefreshTokenEntity refreshToken = new RefreshTokenEntity();

        Date issuedAt = new Date(System.currentTimeMillis());
        refreshToken.setCreatedDate(issuedAt);
        Date expiration = new Date((REFRESH_TOKEN_EXPIRATION_IN_MINUTES * 60 * 1000) + issuedAt.getTime());

        refreshToken.setUser(user);
        refreshToken.setExpiryDate(expiration);
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = tokenRepository.save(refreshToken);
        return refreshToken.getToken();
    }

    @Override
    @Transactional
    public AuthResponse createNewJwtToken(String rfToken) {
        Optional<RefreshTokenEntity> optionalToken = tokenRepository.findByToken(rfToken);

        if (optionalToken.isEmpty()) {
            throw new RuntimeException("Refresh token no encontrado");
        }

        RefreshTokenEntity rft = optionalToken.get();

        validateExpiryDate(rft);

        String token = jwtUtil.createJwtToken(rft.getUser());
        return new AuthResponse(token, rfToken);
    }

    @Override
    @Transactional
    public RefreshTokenEntity updateRefreshToken(String username) {
        RefreshTokenEntity refreshToken = tokenRepository.findByUser_Username(username)
                .orElseThrow(() -> new RuntimeException("Refresh token no encontrado"));
        Date issuedAt = new Date(System.currentTimeMillis());
        refreshToken.setCreatedDate(issuedAt);
        Date expiration = new Date((REFRESH_TOKEN_EXPIRATION_IN_MINUTES*60*1000) + issuedAt.getTime());
        refreshToken.setExpiryDate(expiration);
        refreshToken.setToken(UUID.randomUUID().toString());
        return tokenRepository.save(refreshToken);
    }

    @Override
    public boolean userHasRefreshToken(String username) {
        return tokenRepository.findByUser_Username(username).isPresent();
    }

    @Override
    @Transactional
    public void deleteRefreshToken(String rf) {
        RefreshTokenEntity rfToken = tokenRepository.findByToken(rf)
                .orElseThrow(() -> new RuntimeException("Refresh token no encontrado"));
        tokenRepository.delete(rfToken);
    }


    private void validateExpiryDate(RefreshTokenEntity rfToken) {
        if (rfToken.getExpiryDate().compareTo(new Date()) < 0) {
            tokenRepository.delete(rfToken);
            throw new ExpiredTokenException(rfToken.getToken(), "Refresh Token expirado", EErrorType.REFRESH_TOKEN_EXPIRED);
        }
    }
}