package com.example.demo.util;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTTokenGenerator {

    private static final Logger logger = LoggerFactory.getLogger(JWTTokenGenerator.class);

    @Value("${mydentist.app.jwtSecret}")
    private String jwtSecret;

    @Value("${mydentist.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    private final UserService userService;

    @Autowired
    public JWTTokenGenerator(UserService userService) {
        this.userService = userService;
    }

    public String generateJwtToken(UserDto user) {
        return Jwts.builder()
                .setId(String.valueOf(user.getId()))
                .setSubject((user.getUserName()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public boolean validateJwtToken(String authToken) {
        String jwtToken = authToken.substring("Bearer ".length());
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(jwtToken);
            return true;
        } catch (Exception e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        }
        return false;
    }

}
