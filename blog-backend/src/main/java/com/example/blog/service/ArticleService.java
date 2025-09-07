package com.example.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.blog.entity.Article;
import com.example.blog.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    
    private final ArticleMapper articleMapper;
    
    public List<Article> getAllPublishedArticles() {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("published", true);
        return articleMapper.selectList(queryWrapper);
    }
    
    public Optional<Article> getArticleById(Long id) {
        return Optional.ofNullable(articleMapper.selectById(id));
    }
    
    public Optional<Article> getPublishedArticleById(Long id) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).eq("published", true);
        Article article = articleMapper.selectOne(queryWrapper);
        return Optional.ofNullable(article);
    }
    
    public Article createArticle(Article article) {
        articleMapper.insert(article);
        return article;
    }
    
    public Article updateArticle(Article article) {
        articleMapper.updateById(article);
        return articleMapper.selectById(article.getId());
    }
    
    public void deleteArticle(Long id) {
        articleMapper.deleteById(id);
    }
    
    public List<Article> searchArticles(String keyword) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title", keyword).or().like("content", keyword);
        return articleMapper.selectList(queryWrapper);
    }
    
    public List<Article> getArticlesByCategory(Long categoryId) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId);
        return articleMapper.selectList(queryWrapper);
    }
    
    public List<Article> getArticlesByTag(Long tagId) {
        // 需要实现按标签查询（需要关联表）
        return List.of();
    }
    
    public List<Article> getArticlesByAuthor(Long authorId) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_id", authorId);
        return articleMapper.selectList(queryWrapper);
    }
    
    public Long getPublishedArticleCount() {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("published", true);
        return articleMapper.selectCount(queryWrapper);
    }
    
    public void incrementViewCount(Long articleId) {
        UpdateWrapper<Article> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", articleId).setSql("view_count = view_count + 1");
        articleMapper.update(null, updateWrapper);
    }
}