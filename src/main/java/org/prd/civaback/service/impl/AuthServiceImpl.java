package org.prd.civaback.service.impl;

import org.prd.civaback.persistence.dto.AuthResponse;
import org.prd.civaback.persistence.dto.LoginRequest;
import org.prd.civaback.persistence.entity.RefreshTokenEntity;
import org.prd.civaback.persistence.entity.UserEntity;
import org.prd.civaback.persistence.repository.UserRepository;
import org.prd.civaback.service.AuthService;
import org.prd.civaback.service.RefreshTokenService;
import org.prd.civaback.service.jwt.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    private final HttpSecurity http;

    private final JwtUtil jwtService;

    private final RefreshTokenService refreshTokenService;

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;


    public AuthServiceImpl(UserRepository userRepository, HttpSecurity http, AuthenticationManager authenticationManager,
                           JwtUtil jwtService, RefreshTokenService refreshTokenService) {
        this.userRepository = userRepository;
        this.http = http;
        this.refreshTokenService = refreshTokenService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;

    }

    @Override
    public AuthResponse login(LoginRequest autRequest) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                autRequest.username(), autRequest.password()
        );


        authenticationManager.authenticate(authentication);


        UserEntity user = userRepository.findByUsername(autRequest.username()).orElseThrow(() -> new RuntimeException("User not found"));
        String jwt = jwtService.createJwtToken(user);

        //Si tiene un refresh token, lo actualiza. Si no tiene refresh token, lo crea.
        if(refreshTokenService.userHasRefreshToken(user.getUsername())){
            RefreshTokenEntity rf = refreshTokenService.updateRefreshToken(user.getUsername());
            return new AuthResponse(jwt, rf.getToken());
        }else {
            String rfToken = refreshTokenService.createRefreshToken(user);
            return new AuthResponse(jwt, rfToken);
        }
    }

    @Override
    public void logout(String rf) {
        try{
            refreshTokenService.deleteRefreshToken(rf);
            http.logout(logoutConfig -> {
                logoutConfig.deleteCookies("JSESSIONID")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true);
            });

        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }


}