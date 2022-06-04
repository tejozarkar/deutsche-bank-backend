package com.deutsche.Blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogDto {

    private long id;

    private String title;

    private String description;

    private BlogStatus status;

    private Date createdAt;

    private Date modifiedAt;

    private String username;

}
