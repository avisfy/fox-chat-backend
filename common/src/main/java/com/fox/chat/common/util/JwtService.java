package com.fox.chat.common.util;

import com.fox.chat.common.dto.UserDto;
import com.fox.chat.common.exception.ExpiredJwtTokenException;
import com.fox.chat.common.exception.InvalidJwtTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;


public class JwtService {
    private final SecretKey secretKey;
    private final Long expirationMs;

    public JwtService(String secretKey, Long expirationMs) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.expirationMs = expirationMs;
    }

    public String generateToken(UserDto user) {
        var now = new Date();
        return Jwts
                .builder()
                .subject(user.username())
                .expiration(new Date(now.getTime() + expirationMs))
                .issuedAt(now)
                .signWith(secretKey)
                .compact();
    }


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token) {
        return !isTokenExpired(token);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private boolean isTokenExpired(String token) {
        var expirtionDate = extractClaim(token, Claims::getExpiration);
        return expirtionDate.before(new Date());
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts
                    .parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException ex) {
            throw new ExpiredJwtTokenException();
        } catch (JwtException | IllegalArgumentException ex) {
            throw new InvalidJwtTokenException();
        }
    }

}
