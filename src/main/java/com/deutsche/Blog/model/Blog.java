package com.deutsche.Blog.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

@Entity
@Data
public class Blog {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column( length=10000)
    private String content;

    @Column(columnDefinition = "TEXT")
    private BlogStatus status;

    @ManyToOne
    private User user;

    @CreatedDate
    @Column
    private Date createdAt;

    @LastModifiedDate
    @Column
    private Date modifiedAt;

}
