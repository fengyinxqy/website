package com.example.blog.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ArticleDTO {
    private Long id;
    private String title;
    private String content;
    private String summary;
    private String coverImage;
    private Integer viewCount;
    private Integer likeCount;
    private Boolean published;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserDTO author;
    private List<CategoryDTO> categories;
    private List<TagDTO> tags;
    private List<CommentDTO> comments;
}