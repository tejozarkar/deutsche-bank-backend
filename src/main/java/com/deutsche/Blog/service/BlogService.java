package com.deutsche.Blog.service;

import com.deutsche.Blog.model.Blog;
import com.deutsche.Blog.model.BlogDto;
import com.deutsche.Blog.model.BlogHistory;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface BlogService {

    List<BlogDto> getAllBlogs();
    Blog insertBlog(Blog blog, Authentication authentication);

    String updateBlog(Blog blog, Authentication authentication);

    List<BlogHistory> getBlogHistory(Long blogId);

    String deleteBlog(Long blogId);

    List<BlogDto> getUnderReviewBlogs();

    String publishBlog(Long id);

    List<BlogDto> getUserBlogs();

    Blog getBlogById(Long id);
}
