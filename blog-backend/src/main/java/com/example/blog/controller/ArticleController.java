package com.example.blog.controller;

import com.example.blog.entity.Article;
import com.example.blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {
    
    private final ArticleService articleService;
    
    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAllPublishedArticles();
        return ResponseEntity.ok(articles);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        Optional<Article> article = articleService.getPublishedArticleById(id);
        return article.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        Article savedArticle = articleService.createArticle(article);
        return ResponseEntity.ok(savedArticle);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article article) {
        if (!articleService.getArticleById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        article.setId(id);
        Article updatedArticle = articleService.updateArticle(article);
        return ResponseEntity.ok(updatedArticle);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        if (!articleService.getArticleById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Article>> searchArticles(@RequestParam String keyword) {
        List<Article> articles = articleService.searchArticles(keyword);
        return ResponseEntity.ok(articles);
    }
    
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Article>> getArticlesByCategory(@PathVariable Long categoryId) {
        List<Article> articles = articleService.getArticlesByCategory(categoryId);
        return ResponseEntity.ok(articles);
    }
    
    @GetMapping("/tag/{tagId}")
    public ResponseEntity<List<Article>> getArticlesByTag(@PathVariable Long tagId) {
        List<Article> articles = articleService.getArticlesByTag(tagId);
        return ResponseEntity.ok(articles);
    }
    
    @PostMapping("/{id}/view")
    public ResponseEntity<Void> incrementViewCount(@PathVariable Long id) {
        articleService.incrementViewCount(id);
        return ResponseEntity.ok().build();
    }
}