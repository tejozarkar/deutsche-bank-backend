package com.deutsche.Blog.service.impl;

import com.deutsche.Blog.dao.BlogDao;
import com.deutsche.Blog.dao.BlogHistoryDao;
import com.deutsche.Blog.model.*;
import com.deutsche.Blog.service.BlogService;
import com.deutsche.Blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service(value = "blogService")
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogDao blogDao;

    @Autowired
    BlogHistoryDao blogHistoryDao;

    @Autowired
    UserService userService;

    @Override
    public List<BlogDto> getAllBlogs() {
        return (List<BlogDto>) blogDao.getBlogsByStatus(BlogStatus.PUBLISHED);
    }

    @Override
    public Blog insertBlog(Blog blog, Authentication authentication) {
        blog.setUser(userService.findOne(authentication.getName()));
        blog.setCreatedAt(new Date());
        blog.setModifiedAt(new Date());
        Set<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        System.out.println(roles);
        if (!roles.contains("ROLE_" + UserRole.SUPER_ADMIN.toString())) {
            blog.setStatus(BlogStatus.UNDER_REVIEW);
        } else {
            blog.setStatus(BlogStatus.PUBLISHED);
        }
        return blogDao.save(blog);
    }

    @Override
    public String updateBlog(Blog blog, Authentication authentication) {
        Optional<Blog> oldBlog = blogDao.findById(blog.getId());
        if (oldBlog.isPresent()) {
            blog.setCreatedAt(oldBlog.get().getCreatedAt());
            blog.setModifiedAt(new Date());
            oldBlog.get().setModifiedAt(new Date());
            BlogHistory blogHistory = new BlogHistory(oldBlog.get(), blog);
            blogHistoryDao.save(blogHistory);
        }
        return blogDao.updateBlog(blog.getId(), blog.getTitle(),
                blog.getDescription(), blog.getContent(), blog.getCreatedAt(), blog.getModifiedAt()) + " blog updated";
    }

    @Override
    public List<BlogHistory> getBlogHistory(Long blogId) {
        return blogHistoryDao.findHistoryByBlogId(blogId);
    }

    @Override
    public String deleteBlog(Long blogId) {
        blogHistoryDao.deleteBlog(blogId);
        blogDao.deleteById(blogId);
        return "Blog delete with id : "+blogId;
    }

    @Override
    public List<BlogDto> getUnderReviewBlogs() {
        return blogDao.getBlogsByStatus(BlogStatus.UNDER_REVIEW);
    }

    @Override
    public String publishBlog(Long id) {
        return blogDao.setBlogStatus(BlogStatus.PUBLISHED, id) + " blogs updated!";
    }

    @Override
    public List<BlogDto> getUserBlogs() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        return blogDao.findBlogsByUsername(username);
    }

    @Override
    public Blog getBlogById(Long id) {
       Optional<Blog> blog =  blogDao.findById(id);
       if(blog.isPresent())
         return blog.get();
       throw new NoSuchElementException("Blog not found");
    }
}
