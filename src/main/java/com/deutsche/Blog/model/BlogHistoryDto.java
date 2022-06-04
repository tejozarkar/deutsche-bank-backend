package com.deutsche.Blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogHistoryDto {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String content;

    @Column
    private BlogStatus status;

    @ManyToOne
    private User user;


    public BlogHistoryDto(BlogHistory blog){
        this.title = blog.getTitle();
        this.description = blog.getDescription();
        this.content = blog.getContent();
        this.status = blog.getStatus();
        this.user = blog.getUser();
    }

}
