//package com.example.Boutique_Final.dto;
//
//import jakarta.validation.constraints.Positive;
//import lombok.Data;
//
//import java.math.BigDecimal;
//
//@Data
//public class OrderItemDTO {
//    private Long id;
//    private Long productId;
//    @Positive
//    private Integer quantity;
//    @Positive
//    private BigDecimal price;
//}



package com.example.Boutique_Final.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private String productId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
}
