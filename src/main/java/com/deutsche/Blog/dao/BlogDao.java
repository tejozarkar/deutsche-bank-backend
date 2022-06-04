package com.deutsche.Blog.dao;

import com.deutsche.Blog.model.Blog;
import com.deutsche.Blog.model.BlogDto;
import com.deutsche.Blog.model.BlogStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface BlogDao extends CrudRepository<Blog, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Blog b set" +
            " b.title = :title, " +
            " b.description = :description, " +
            " b.content = :content, " +
            " b.modifiedAt = :modifiedAt, " +
            " b.createdAt = :createdAt " +
            " where b.id = :id")
    Integer updateBlog(Long id, String title, String description, String content, Date createdAt, Date modifiedAt);

    @Transactional
    @Modifying
    @Query("SELECT new com.deutsche.Blog.model.BlogDto(b.id, b.title, b.description, b.status, b.createdAt, b.modifiedAt, b.user.username) FROM Blog b where b.status = :status")
    List<BlogDto> getBlogsByStatus(BlogStatus status);

    @Transactional
    @Modifying
    @Query("UPDATE Blog b set b.status = :status where id  = :id")
    Integer setBlogStatus(BlogStatus status, Long id);

    @Transactional
    @Modifying
    @Query("SELECT new com.deutsche.Blog.model.BlogDto(b.id, b.title, b.description, b.status,b.createdAt, b.modifiedAt, b.user.username) FROM Blog b WHERE b.user.username = :username")
    List<BlogDto> findBlogsByUsername(String username);
}
