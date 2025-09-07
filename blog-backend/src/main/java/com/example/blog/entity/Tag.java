package com.example.blog.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Tag {
    private Long id;

    @NotBlank
    private String name;

    private String description;
    private String slug;

    private LocalDateTime createdAt;

    private List<Article> articles;

    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}