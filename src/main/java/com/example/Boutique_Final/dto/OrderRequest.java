package com.example.Boutique_Final.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private String userId;
    private String address;
    private String phoneNumber;
}
