package com.example.Boutique_Final.Controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import com.example.Boutique_Final.dto.*;
import com.example.Boutique_Final.model.User;
import com.example.Boutique_Final.service.AuthService;
import com.example.Boutique_Final.service.UserService;
import com.example.Boutique_Final.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import com.example.Boutique_Final.exception.ResourceNotFoundException;
import java.util.HashMap;
import java.util.Map;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:3000", "https://alagar003.github.io/Botique_VTS/"}, allowCredentials = "true")

@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Value("${admin.organization.password}")
    private String adminOrgPassword;  // Loaded from application properties

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody RegisterRequest request) {
        // Validate organization password for admin registration
        if ("ADMIN".equalsIgnoreCase(request.getRole())) {
            if (request.getOrganizationPassword() == null || !request.getOrganizationPassword().equals(adminOrgPassword)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(Map.of("message", "Invalid organization password for admin registration."));
            }
        }

        userService.registerUser(request);
        return ok(Map.of("message", "User registered successfully!"));
    }

    // 🟢 Email Confirmation
    @PostMapping("/confirm-email")
    public ResponseEntity<Map<String, String>> confirmEmail(@RequestBody EmailConfirmationRequest request) {
        try {
            userService.confirmEmail(request.getEmail(), request.getConfirmationCode());
            return ok(Map.of("message", "Email confirmed successfully."));
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Invalid confirmation code."));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Email not found."));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request) {
        try {
            User user = userService.loginUser(request.getEmail(), request.getPassword()); // Use email

            String token = jwtService.generateToken(user); // Pass the User object directly


            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("userId", (user.getId() instanceof ObjectId) ? ((ObjectId) user.getId()).toHexString() : user.getId());
            response.put("user", user);

            return ResponseEntity.ok(response);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Login failed"));
        }
    }


    @PostMapping("/change-password")
    public ResponseEntity<Map<String, String>> changePassword(@RequestBody ChangePasswordRequest request) {
        userService.changePassword(request);
        return ok(Map.of("message", "Password changed successfully."));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Map<String, String>> forgotPassword(@RequestBody Map<String, String> payload) {
        userService.forgotPassword(payload.get("email"));
        return ok(Map.of("message", "Password reset email sent."));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, String>> resetPassword(@RequestBody Map<String, String> payload) {
        userService.resetPassword(payload.get("email"), payload.get("resetToken"), payload.get("newPassword"));
        return ok(Map.of("message", "Password reset successfully."));
    }
}
