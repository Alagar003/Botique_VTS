package com.example.Boutique_Final.Controller;

import com.example.Boutique_Final.dto.EmailRequest;
import com.example.Boutique_Final.model.User;
import com.example.Boutique_Final.service.JwtService;
import com.example.Boutique_Final.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "https://alagar003.github.io/Botique_VTS/"}, allowCredentials = "true")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    // Endpoint to get a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    //update
    @PostMapping("/role")
    public ResponseEntity<String> getUserRole(@Valid @RequestBody EmailRequest request) {
        User user = userService.getUserByEmail(request.getEmail());

        if (user != null) {
            String role = String.valueOf(user.getRole());
            return ResponseEntity.ok(role);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/user")
    public ResponseEntity<?> getCurrentUser(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Missing or invalid Authorization header");
        }

        try {
            String token = authHeader.substring(7);
            String username = jwtService.extractUsername(token); // ✅ Extracting username

            User user = userService.getUserByUsername(username); // ✅ Fetching by username
            if (user != null) {
                Map<String, Object> userResponse = new HashMap<>();
                userResponse.put("id", user.getId().toString());
                userResponse.put("name", user.getUsername());
                userResponse.put("email", user.getEmail());
                userResponse.put("role", user.getRole());

                return ResponseEntity.ok(userResponse);
            } else {
                return ResponseEntity.status(404).body("User not found");
            }
        } catch (ExpiredJwtException e) {
            return ResponseEntity.status(401).body("Token expired");
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid or expired token");
        }
    }





}



