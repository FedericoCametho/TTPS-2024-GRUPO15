package com.TTPS2024.buffet.service.usuario.login;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.logging.Logger;

@Service
public class TokenService {
    private static final Logger LOGGER = Logger.getLogger(TokenService.class.getName());
    final static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(String email, int expirationInSec){
        Date expiration = new Date(System.currentTimeMillis() + expirationInSec * 1000);
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    public static boolean validateToken(String token){
        String prefix = "Bearer";
        try {
            if (token.startsWith(prefix)) {
                token = token.substring(prefix.length()).trim();
            }
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            LOGGER.info("Token valido");
            LOGGER.info("Subject: " + claims.getSubject());
            LOGGER.info("Expiration: " + claims.getExpiration());
            return true;
        }catch (JwtException exp){
            return false;
        }
    }

}
