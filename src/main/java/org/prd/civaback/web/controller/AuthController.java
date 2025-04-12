package org.prd.civaback.web.controller;

import jakarta.validation.Valid;
import org.prd.civaback.persistence.dto.AuthResponse;
import org.prd.civaback.persistence.dto.LoginRequest;
import org.prd.civaback.persistence.dto.RefreshTokenRequest;
import org.prd.civaback.service.AuthService;
import org.prd.civaback.service.RefreshTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    public AuthController(AuthService authService, RefreshTokenService refreshTokenService) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@Valid @RequestBody LoginRequest loginRequest){
        AuthResponse rsp = authService.login(loginRequest);
        return ResponseEntity.ok(rsp);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@Valid @RequestBody RefreshTokenRequest rf){
        authService.logout(rf.refreshToken());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshTokenEndPoint(@Valid @RequestBody RefreshTokenRequest rf){

        AuthResponse authResponse = refreshTokenService.createNewJwtToken(rf.refreshToken());
        return ResponseEntity.ok(authResponse);

    }
}