package com.deutsche.Blog.controller;

import com.deutsche.Blog.model.Blog;
import com.deutsche.Blog.model.Message;
import com.deutsche.Blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping("/details/{id}")
    public ResponseEntity<?> getBlogDetails(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.getBlogById(id));
    }

    @PostMapping
    public ResponseEntity<?> insertBlog(@RequestBody Blog blog, Authentication authentication) {
        return ResponseEntity.ok(blogService.insertBlog(blog, authentication));
    }

    @GetMapping
    public ResponseEntity<?> getAllBlogs() {
        return ResponseEntity.ok(blogService.getAllBlogs());
    }

    @PostMapping("/edit")
    public ResponseEntity<?> updateBlog(@RequestBody Blog blog, Authentication authentication) {
        return ResponseEntity.ok(new Message(blogService.updateBlog(blog, authentication)));
    }

    @GetMapping("/history/{blogId}")
    public ResponseEntity<?> getBlogHistory(@PathVariable Long blogId) {
        return ResponseEntity.ok(blogService.getBlogHistory(blogId));
    }

    @GetMapping("/status/under_review")
    public ResponseEntity<?> getUnderReviewBlogs() {
        return ResponseEntity.ok(blogService.getUnderReviewBlogs());
    }

    @PostMapping("/status/published/{id}")
    public ResponseEntity<?> publishBlog(@PathVariable Long id) {
        return ResponseEntity.ok(new Message(blogService.publishBlog(id)));
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUserBlogs() {
        return ResponseEntity.ok(blogService.getUserBlogs());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Long id) {
        return ResponseEntity.ok(new Message(blogService.deleteBlog(id)));
    }

}
