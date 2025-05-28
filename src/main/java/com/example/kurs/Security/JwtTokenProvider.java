package com.example.kurs.Security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class JwtTokenProvider {

    // Используем статический ключ вместо генерации нового при каждом запуске
    private final String SECRET_KEY = "OGYzMTVjYzEtNjljZi00ZmI2LTg4NzItYWVmMTRiM2I5ZmQ1";
    private final SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    private final long validityInMilliseconds = 3600000; // 1 час

    public String generateToken(String username) {
        log.info("Generating token for user: {}", username);
        Claims claims = Jwts.claims().setSubject(username);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getUsername(String token) {
        try {
            String username = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
            log.info("Extracted username from token: {}", username);
            return username;
        } catch (JwtException | IllegalArgumentException e) {
            log.error("Failed to extract username from token", e);
            return null;
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            log.info("Token validation successful");
            return true;
        } catch (ExpiredJwtException e) {
            log.error("JWT token expired", e);
            return false;
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token", e);
            return false;
        } catch (MalformedJwtException e) {
            log.error("Malformed JWT token", e);
            return false;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature", e);
            return false;
        } catch (JwtException | IllegalArgumentException e) {
            log.error("Invalid JWT token", e);
            return false;
        }
    }
}

