package com.example.Boutique_Final.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private String id;
    @NotBlank(message = "Content is required")
    private String content;
    @Min(value = 1)
    @Max(value = 5)
    private Integer score;
    private String userId;

    // New constructor without score (optional if you don't want to use score)
    public CommentDTO(String id, String content, String userId) {
        this.id = id;
        this.content = content;
        this.userId = userId;
    }
}
