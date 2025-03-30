package com.example.Boutique_Final.service;

import com.example.Boutique_Final.dto.ChangePasswordRequest;
import com.example.Boutique_Final.dto.RegisterRequest;
import com.example.Boutique_Final.exception.ResourceNotFoundException;
import com.example.Boutique_Final.model.User;
import com.example.Boutique_Final.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Value("${admin.organization.password}")  // Inject organization password from properties
    private String adminOrgPassword;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }


    @Value("${admin.organization.password}")  // Inject organization password from properties
    private String organizationPassword;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);





    // 🟢 Register New User
    public User registerUser(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalStateException("Email already taken.");
        }

        User.Role role;
        try {
            role = User.Role.valueOf(request.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Invalid role selected.");
        }

        User user = new User();
        user.setUsername(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);
        user.setConfirmationCode(generateConfirmationCode());
        user.setEmailConfirmation(false);

        emailService.sendConfirmationCode(user);

        return userRepository.save(user);  // ✅ Now returning User
    }




    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // 🔴 Check if email is verified
        if (!user.isEmailConfirmation()) {
            throw new IllegalStateException("Email not verified. Please check your inbox.");
        }

        // ✅ Check password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        return user;
    }


    // 🟢 Generate Email Confirmation Code
    private String generateConfirmationCode() {
        return UUID.randomUUID().toString();
    }
    // 🟢 Change Password
    public void changePassword(ChangePasswordRequest request) {
        // Fetch the user by email
        User user = getUserByEmail(request.getEmail());

        // Verify current password matches the stored password
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            logger.warn("Password mismatch for user: {}", request.getEmail());
            throw new BadCredentialsException("Current password is incorrect");
        }

        // Update the password: encode the new password and save it
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        logger.info("Password changed successfully for user: {}", user.getEmail());
    }

    // 🟢 Confirm Email
    public void confirmEmail(String email, String confirmationCode) {
        User user = getUserByEmail(email);

        // Validate confirmation code
        if (user.getConfirmationCode() != null && user.getConfirmationCode().equals(confirmationCode)) {
            user.setEmailConfirmation(true);
            user.setConfirmationCode(null);  // Clear confirmation code
            userRepository.save(user);
            logger.info("Email confirmed for user: {}", email);
        } else {
            logger.warn("Invalid confirmation code for user: {}", email);
            throw new BadCredentialsException("Invalid confirmation code");
        }
    }

    public void forgotPassword(String email) {
        // Fetch the user by email
        User user = getUserByEmail(email);

        // Check if the email is verified
        if (!user.isEmailConfirmation()) {
            throw new IllegalStateException("Email not verified. Please verify your email before requesting a password reset.");
        }

        // Generate a reset token
        String resetToken = UUID.randomUUID().toString();

        // Set the reset token and its expiration (using dedicated fields)
        user.setResetToken(resetToken);
        user.setResetTokenExpiration(Instant.now().plus(Duration.ofMinutes(15))); // Token valid for 15 minutes

        // Save the user with the new reset token details
        userRepository.save(user);

        // Send email with reset link
        try {
            emailService.sendPasswordResetEmail(user, resetToken);
        } catch (Exception e) {
            logger.error("Failed to send password reset email to {}: {}", email, e.getMessage());
            throw new RuntimeException("Failed to send password reset email.");
        }

        logger.info("Password reset email sent to {}", email);
    }

    public void resetPassword(String email, String resetToken, String newPassword) {
        // Fetch the user by email
        User user = getUserByEmail(email);

        // Check if the email is verified
        if (!user.isEmailConfirmation()) {
            throw new IllegalStateException("Email not verified. Please verify your email before resetting your password.");
        }

        // Debug logs: Log the stored token and the received token (remove these in production)
        System.out.println("Stored reset token: " + user.getResetToken());
        System.out.println("Stored token expiration: " + user.getResetTokenExpiration());
        System.out.println("Received reset token: " + resetToken);

        // Validate that the resetToken from the payload is provided
        if (resetToken == null || resetToken.trim().isEmpty()) {
            throw new IllegalArgumentException("Reset token must be provided.");
        }

        // Validate the reset token against the stored token
        if (user.getResetToken() == null || !user.getResetToken().equals(resetToken.trim())) {
            throw new IllegalArgumentException("Invalid or expired reset token.");
        }

        // Validate token expiration (if using an expiration mechanism)
        if (user.getResetTokenExpiration() == null || user.getResetTokenExpiration().isBefore(Instant.now())) {
            throw new IllegalArgumentException("Invalid or expired reset token.");
        }

        // Update password and clear the reset token fields
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setResetTokenExpiration(null);
        userRepository.save(user);

        logger.info("Password reset successfully for {}", email);
    }

    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    // 🟢 Get All Users
    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        return userRepository.findAll();
    }

    // 🟢 Get User Role by Email
    public String getUserRoleByEmail(String email) {
        User user = getUserByEmail(email);
        return user.getRole().toString();
    }

    // 🟢 Get User by Email
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }

    public User getUserByUsername(String username) {
        log.debug("🔍 Searching for user with username: {}", username);

        List<User> allUsers = userRepository.findAll();
        log.debug("📋 All users in DB: {}", allUsers);

        Optional<User> user = userRepository.findByEmail(username); // Ensure it searches by email
        if (user.isEmpty()) {
            log.error("❌ User '{}' not found in the database!", username);
        }
        return user.orElse(null);
    }



}