package com.deutsche.Blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogHistory {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    private BlogStatus status;

    @ManyToOne
    private User user;

    @ManyToOne
    private Blog originalBlog;

    @CreatedDate
    @Column
    private Date createdAt;

    @LastModifiedDate
    @Column
    private Date modifiedAt;

    public BlogHistory(Blog blog, Blog originalBlog){
        this.title = blog.getTitle();
        this.description = blog.getDescription();
        this.content = blog.getContent();
        this.status = blog.getStatus();
        this.user = blog.getUser();
        this.originalBlog = originalBlog;
        this.createdAt = blog.getCreatedAt();
        this.modifiedAt = blog.getModifiedAt();
    }

}
