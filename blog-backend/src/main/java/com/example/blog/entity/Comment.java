package com.example.blog.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Comment {
    private Long id;

    @NotBlank
    private String content;

    private LocalDateTime createdAt;

    private Article article;
    private User user;
    private Comment parent;

    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}