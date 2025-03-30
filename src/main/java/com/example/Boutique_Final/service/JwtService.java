package com.example.Boutique_Final.service;//// JwtService.java
import com.example.Boutique_Final.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

import static javax.crypto.Cipher.SECRET_KEY;

@Service
public class JwtService {

    private String secretKey = "your-secret-key";

    @Value("${jwt.expiration}")
    private long jwtExpirationInMillis;

    private static final Logger log = LoggerFactory.getLogger(JwtService.class);


    public String generateToken(UserDetails userDetails) {
        User user = (User) userDetails; // Assuming you have a User class
        return Jwts.builder()
                .setSubject(user.getEmail())  // Keep email as subject
                .claim("username", user.getUsername()) // 🔹 Add username as a claim
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMillis)) // 10 hours
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }








    public String extractUsername(String token) {
        try {
            String username = extractClaims(token, Claims::getSubject);
            log.debug("✅ Extracted username from token: {}", username);
            return username;
        } catch (ExpiredJwtException e) {
            log.error("⏳ Token expired: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("❌ Failed to extract username: {}", e.getMessage());
            throw e;
        }
    }





    public String extractEmail(String token) {
        return extractClaims(token, Claims::getSubject); // ✅ Extracts email correctly
    }




    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String extractedEmail = extractUsername(token);  // This is actually the email
        log.debug("Extracted email: {}", extractedEmail);
        log.debug("UserDetails email: {}", ((User) userDetails).getEmail());

        return extractedEmail.equals(((User) userDetails).getEmail()) && !isTokenExpired(token);
    }






    private boolean isTokenExpired(String token) {
        Date expiration = extractClaims(token, Claims::getExpiration);
        log.debug("Token expiration time: {}", expiration);
        return expiration.before(new Date());
    }

}
