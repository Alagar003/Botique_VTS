package com.example.Boutique_Final.service;//package com.example.Boutique_Final.service;
import com.example.Boutique_Final.dto.EmailConfirmationRequest;
import com.example.Boutique_Final.dto.LoginRequest;
import com.example.Boutique_Final.dto.RegisterRequest;
import com.example.Boutique_Final.model.User;
import com.example.Boutique_Final.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;

    @Value("${admin.organization.password}")
    private String adminPassword;  // Load the admin password from properties

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository,
                       PasswordEncoder passwordEncoder, JwtService jwtService, EmailService emailService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.emailService = emailService;
    }

    public String authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new IllegalStateException("User not found"));

        return jwtService.generateToken(user); // Pass the whole User object
    }

    public void registerUser(RegisterRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new IllegalStateException("Email already taken");
        }

        // Set default role
        User.Role role = User.Role.CUSTOMER;

        // Check if registering as admin
        if ("ADMIN".equalsIgnoreCase(registerRequest.getRole())) {
            if (!registerRequest.getOrganizationPassword().equals(adminPassword)) {
                throw new IllegalStateException("Invalid admin password");
            }
            role = User.Role.ADMIN;
        }

        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(role);  // Set role dynamically

        userRepository.save(user);
        emailService.sendConfirmationCode(user);
    }

    public void confirmEmail(EmailConfirmationRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalStateException("User not found"));
        if (user.getConfirmationCode().equals(request.getConfirmationCode())) {
            user.setEmailConfirmation(true);
            userRepository.save(user);
        } else {
            throw new IllegalStateException("Invalid confirmation code");
        }
    }
}

