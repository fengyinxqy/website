package com.example.blog.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TagDTO {
    private Long id;
    private String name;
    private String slug;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}