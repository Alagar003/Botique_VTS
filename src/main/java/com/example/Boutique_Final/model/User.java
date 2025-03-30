//package com.example.Boutique_Final.model;
//
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.bson.types.ObjectId;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.DBRef;
//
//import java.time.Instant;
//import java.util.Collection;
//import java.util.List;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Document(collection = "users")
//public class User implements UserDetails {
//
//
//    @Id
//    private ObjectId id;
//
////    private Object id;  // Use String for MongoDB's ObjectId
//
//    @NotBlank
//    @Email
//    private String email;
//
//    private String username;
//    @NotBlank
//    private String password;
//
//
//    private Role role = Role.CUSTOMER;  // Default role as CUSTOMER
//
//    @DBRef(lazy = true)
//    private Cart cart;
//
//    private boolean emailConfirmation = false;  // Default to false
//
//    private String confirmationCode;  // Token for email confirmation
//
//    // New fields for password reset
//    private String resetToken;               // Token for password reset
//    private Instant resetTokenExpiration;    // Expiration time for the reset token
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    public void setUsername(String username) {
//        this.email = username;  // Since username is derived from email
//    }
//
//
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    public ObjectId getId() {
//        return id;
//    }
//
//    public void setId(ObjectId id) {
//        this.id = id;
//    }
//
//    public @NotBlank @Email String getEmail() {
//        return email;
//    }
//
//    public void setEmail(@NotBlank @Email String email) {
//        this.email = email;
//    }
//
//    public @NotBlank String getPassword() {
//        return password;
//    }
//
//    public void setPassword(@NotBlank String password) {
//        this.password = password;
//    }
//
//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//
//    public Cart getCart() {
//        return cart;
//    }
//
//    public void setCart(Cart cart) {
//        this.cart = cart;
//    }
//
//    public boolean isEmailConfirmation() {
//        return emailConfirmation;
//    }
//
//    public void setEmailConfirmation(boolean emailConfirmation) {
//        this.emailConfirmation = emailConfirmation;
//    }
//
//    public String getConfirmationCode() {
//        return confirmationCode;
//    }
//
//    public void setConfirmationCode(String confirmationCode) {
//        this.confirmationCode = confirmationCode;
//    }
//
//    public String getResetToken() {
//        return resetToken;
//    }
//
//    public void setResetToken(String resetToken) {
//        this.resetToken = resetToken;
//    }
//
//    public Instant getResetTokenExpiration() {
//        return resetTokenExpiration;
//    }
//
//    public void setResetTokenExpiration(Instant resetTokenExpiration) {
//        this.resetTokenExpiration = resetTokenExpiration;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        // User can log in only if email is confirmed
//        return emailConfirmation;
//    }
//
//    public enum Role {
//        CUSTOMER,
//        ADMIN
//    }
//}


package com.example.Boutique_Final.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User implements UserDetails {

    @Id
    private ObjectId id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String username; // Ensure username is required

    @NotBlank
    private String password;

    private Role role = Role.CUSTOMER;  // Default role as CUSTOMER

    @DBRef(lazy = true)
    private Cart cart;

    private boolean emailConfirmation = false;  // Default to false

    private String confirmationCode;  // Token for email confirmation

    // New fields for password reset
    private String resetToken;
    private Instant resetTokenExpiration;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return email;  // Return username instead of email
    }

    public void setUsername(String username) {
        this.username = username;  // Now username is properly set
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return emailConfirmation;
    }

    public enum Role {
        CUSTOMER,
        ADMIN
    }
}
