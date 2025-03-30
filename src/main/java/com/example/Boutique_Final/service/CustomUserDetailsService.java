package com.example.Boutique_Final.service;

import com.example.Boutique_Final.model.User;
import com.example.Boutique_Final.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    private final UserRepository userRepository; // Constructor injection

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        logger.info("Attempting to load user by email/username: {}", identifier);
        return userRepository.findByEmail(identifier)
                .or(() -> userRepository.findByUsername(identifier)) // Support username lookup if needed
                .map(user -> {
                    logger.info("User found: {}", identifier);
                    return user;
                })
                .orElseThrow(() -> {
                    logger.error("User not found: {}", identifier);
                    return new UsernameNotFoundException("User not found: " + identifier);
                });
    }
}
