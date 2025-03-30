package com.example.Boutique_Final.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import javax.management.relation.Role;

@Data
public class UserRequestDTO {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    private Role role; // CUSTOMER or ADMIN

    private String organizationPassword; // Required only if role = ADMIN


}
