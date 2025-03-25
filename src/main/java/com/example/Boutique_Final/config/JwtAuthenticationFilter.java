//package com.example.Boutique_Final.config;
//
//import com.example.Boutique_Final.service.JwtService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
//    private final JwtService jwtService;
//    private final UserDetailsService userDetailsService;
//
//    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
//        this.jwtService = jwtService;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        final String authorizationHeader = request.getHeader("Authorization");
//
//        String jwt = null;
//        String username = null;
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            jwt = authorizationHeader.substring(7); // Remove "Bearer " prefix
//            try {
//                username = jwtService.extractUsername(jwt); // Extract username from token
//            } catch (Exception e) {
//                logger.error("Error extracting username: {}", e.getMessage());
//            }
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//            if (jwtService.validateToken(jwt, userDetails.getUsername())) {
//                var authToken = new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//                logger.info("User {} authenticated successfully", username);
//            } else {
//                logger.error("Invalid token for user: {}", username);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        final String authorizationHeader = request.getHeader("Authorization");
//
//        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String jwt = authorizationHeader.substring(7);
//        String username = null;
//        String role = null;
//
//        try {
//            username = jwtService.extractUsername(jwt);
//            role = jwtService.extractRole(jwt); // Extract role
//        } catch (Exception e) {
//            logger.error("Error extracting claims from token: {}", e.getMessage());
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//            if (jwtService.validateToken(jwt, userDetails)) {
//                List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));
//
//                UsernamePasswordAuthenticationToken authToken =
//                        new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//                logger.info("User {} authenticated successfully with role: {}", username, role);
//            } else {
//                logger.error("Invalid token for user: {}", username);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//
//}
//
//
//
////
////package com.example.Boutique_Final.config;
////
////import com.example.Boutique_Final.service.JwtService;
////import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;
////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
////import org.springframework.security.core.GrantedAuthority;
////import org.springframework.security.core.authority.SimpleGrantedAuthority;
////import org.springframework.security.core.context.SecurityContextHolder;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
////import org.springframework.web.filter.OncePerRequestFilter;
////
////import jakarta.servlet.FilterChain;
////import jakarta.servlet.ServletException;
////import jakarta.servlet.http.HttpServletRequest;
////import jakarta.servlet.http.HttpServletResponse;
////import java.io.IOException;
////import java.util.Collections;
////import java.util.List;
////
////public class JwtAuthenticationFilter extends OncePerRequestFilter {
////
////    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
////    private final JwtService jwtService;
////    private final UserDetailsService userDetailsService;
////
////    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
////        this.jwtService = jwtService;
////        this.userDetailsService = userDetailsService;
////    }
////
////    @Override
////    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
////            throws ServletException, IOException {
////        final String authorizationHeader = request.getHeader("Authorization");
////
////        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
////            logger.warn("No JWT token found in request headers");
////            filterChain.doFilter(request, response);
////            return;
////        }
////
////        String jwt = authorizationHeader.substring(7);
////        String username = null;
////        String role = null;
////
////        try {
////            username = jwtService.extractUsername(jwt);
////            role = jwtService.extractRole(jwt); // Extract role
////        } catch (Exception e) {
////            logger.error("Error extracting claims from token: {}", e.getMessage());
////            filterChain.doFilter(request, response);
////            return;
////        }
////
////        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
////            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
////
////            if (jwtService.validateToken(jwt, userDetails)) {
////                List<GrantedAuthority> authorities = (role != null)
////                        ? List.of(new SimpleGrantedAuthority("ROLE_" + role))
////                        : Collections.emptyList();
////
////                UsernamePasswordAuthenticationToken authToken =
////                        new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
////                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////
////                SecurityContextHolder.getContext().setAuthentication(authToken);
////                logger.info("User '{}' authenticated successfully with role: {}", username, role);
////            } else {
////                logger.warn("Invalid or expired token for user: {}", username);
////                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
////                response.getWriter().write("Token expired or invalid. Please log in again.");
////                return;
////            }
////        }
////
////        filterChain.doFilter(request, response);
////    }
////}



package com.example.Boutique_Final.config;

import com.example.Boutique_Final.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String jwt = null;
        String username = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); // Remove "Bearer " prefix
            try {
                username = jwtService.extractUsername(jwt); // Extract username from token
            } catch (Exception e) {
                logger.error("Error extracting username: {}", e.getMessage());
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtService.validateToken(jwt, userDetails)) {
                var authToken = new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                logger.info("User {} authenticated successfully", username);
            } else {
                logger.error("Invalid token for user: {}", username);
            }
        }

        filterChain.doFilter(request, response);
    }
}