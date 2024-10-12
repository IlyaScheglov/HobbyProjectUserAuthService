package com.github.ilyxahobby.UserAuthService.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SecretKeyBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.crypto.spec.SecretKeySpec;
import java.time.Duration;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.lifetime}")
    private Duration jwtLifetime;

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generateJwt(UUID userId, String login) {
        var issuedDate = new Date();
        var expiredDate = new Date(issuedDate.getTime() + jwtLifetime.toMillis());

        return Jwts.builder()
                .setClaims(Map.of("usrid", userId))
                .setSubject(login)
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public UUID getIdFromToken(String token) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(jwtSecret.getBytes("UTF-8"), "HmacSHA256");
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getBody()
                    .get("usrid", UUID.class);
        } catch (Exception e) {
            return null;
        }
    }
}
