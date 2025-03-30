package com.example.Boutique_Final.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String secretKey;

    // Injecting the secret key via constructor (fix for @Value on static field)
    public JwtUtil(@Value("${jwt.secret}") String secretKey) {
        this.secretKey = secretKey;
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();  // Make sure "sub" contains email when creating JWT
    }

    public Date extractExpiration(String token) {
        return extractClaims(token).getExpiration();
    }

    public String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    public boolean isTokenValid(String token) {
        try {
            return !isTokenExpired(token);
        } catch (ExpiredJwtException e) {
            System.err.println("Expired token: " + e.getMessage());
        } catch (SignatureException e) {
            System.err.println("Invalid JWT signature: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Invalid token: " + e.getMessage());
        }
        return false;
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
