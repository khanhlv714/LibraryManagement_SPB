package com.example.library.security;


import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {


    private final SecretKey secretKey =
            Keys.secretKeyFor(SignatureAlgorithm.HS256);


    private final long accessTokenExpiration =
            10000 * 60 * 15; // 150 phút


    private final long refreshTokenExpiration =
            1000L * 60 * 60 * 24 * 7; // 7 ngày



    public String generateToken(
            String username,
            String role,
            Long securityVersion) {



        return Jwts.builder()
                .subject(username)
                .claim("role", role)
                .claim("securityVersion", securityVersion)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                            System.currentTimeMillis()
                            + accessTokenExpiration
                        )
                )
                .signWith(secretKey)
                .compact();
    }



    public String generateRefreshToken(
            String username,
            Long securityVersion) {


        return Jwts.builder()
                .subject(username)
                .claim("securityVersion",securityVersion)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                            System.currentTimeMillis()
                            + refreshTokenExpiration
                        )
                )
                .signWith(secretKey)
                .compact();
    }



    public String extractUsername(String token) {

        Claims claims = extractClaims(token);

        return claims.getSubject();
    }

    public String extractRole(String token) {

        Claims claims = extractClaims(token);

        return claims.get("role", String.class);
    }

    public Long extractSecurityVersion(String token) {

        Claims claims = extractClaims(token);

        return claims.get("securityVersion", Long.class);
    }



    private Claims extractClaims(String token) {

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }



    public boolean isTokenValid(String token) {

        try {

            extractClaims(token);

            return true;

        } catch (Exception e) {

            return false;
        }
    }
}