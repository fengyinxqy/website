package com.example.blog.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Article {
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private String summary;
    private String coverImage;
    private Integer viewCount = 0;
    private Integer likeCount = 0;
    private Boolean published = false;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private User author;
    private List<Tag> tags;
    private List<Category> categories;
    private List<Comment> comments;
}