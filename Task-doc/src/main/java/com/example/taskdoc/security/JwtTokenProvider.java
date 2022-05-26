package com.example.taskdoc.security;

import com.example.taskdoc.model.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {


    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken(User user) {
        Date durationTimeLife = new Date(new Date().getTime() + jwtExpirationInMs);
        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .claim("role", user.getRoles())
                .claim("Fullname", user.getFullName())
                .setIssuedAt(new Date())
                .setExpiration(durationTimeLife)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
