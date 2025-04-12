package org.prd.civaback.service.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.prd.civaback.persistence.entity.UserEntity;
import org.prd.civaback.util.EErrorType;
import org.prd.civaback.config.exception.ExpiredTokenException;
import org.slf4j.Logger;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${security.jwt.token-expiration-in-minutes}")
    private Long TOKEN_EXPIRATION_IN_MINUTES;


    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;


    public String createJwtToken(UserEntity user) {

        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date( (TOKEN_EXPIRATION_IN_MINUTES*60*1000) + issuedAt.getTime() );

        return Jwts.builder()
                .header().type("JWT").and()
                .subject(user.getUsername())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .claims(generateJwtAccessExtraClaims(user))
                .signWith(generateKey(), Jwts.SIG.HS256)
                .compact();
    }

    private Map<String, Object> generateJwtAccessExtraClaims(UserEntity user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("username",user.getUsername());
        extraClaims.put("role",user.getAuthorities());
        return extraClaims;
    }


    private Claims extractAllClaims(String jwt) {
        try{
            return Jwts.parser().verifyWith(generateKey()).build()
                    .parseSignedClaims(jwt).getPayload();
        } catch (ExpiredJwtException e) {
            log.warn("token expirado");
            throw new ExpiredTokenException(jwt, "Token expirado", EErrorType.ACCESS_TOKEN_EXPIRED);
        } catch (UnsupportedJwtException e) {
            log.warn("token no soportado");
        } catch (MalformedJwtException e) {
            log.warn("token malformado");
        } catch (IllegalArgumentException e) {
            log.warn("illegal args");
        }
        throw new RuntimeException("Token inválido");
    }

    public String extractUsername(String jwt) {
        return extractAllClaims(jwt).getSubject();
    }



    public String extractJwtFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if(!StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")){
            return null;
        }
        return authorizationHeader.split(" ")[1];
    }


    private SecretKey generateKey() {
        byte[] passwordDecoded = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(passwordDecoded);
    }
}