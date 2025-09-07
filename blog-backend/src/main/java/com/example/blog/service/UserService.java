package com.example.blog.service;

import com.example.blog.entity.User;
import com.example.blog.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    
    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userMapper.selectById(id));
    }
    
    public Optional<User> getUserByUsername(String username) {
        return Optional.ofNullable(userMapper.selectOne(
            new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<User>()
                .eq(User::getUsername, username)
        ));
    }
    
    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(userMapper.selectOne(
            new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<User>()
                .eq(User::getEmail, email)
        ));
    }
    
    public User createUser(User user) {
        // 加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insert(user);
        return user;
    }
    
    public User updateUser(User user) {
        userMapper.updateById(user);
        return user;
    }
    
    public void deleteUser(Long id) {
        userMapper.deleteById(id);
    }
    
    public boolean usernameExists(String username) {
        return userMapper.selectCount(
            new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<User>()
                .eq(User::getUsername, username)
        ) > 0;
    }
    
    public boolean emailExists(String email) {
        return userMapper.selectCount(
            new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<User>()
                .eq(User::getEmail, email)
        ) > 0;
    }
    
    public boolean validatePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}