package com.deutsche.Blog.service;

import com.deutsche.Blog.model.User;
import com.deutsche.Blog.model.UserDto;

import java.util.List;

public interface UserService {
    User save(UserDto user);
    List<User> findAll();
    User findOne(String username);
}
