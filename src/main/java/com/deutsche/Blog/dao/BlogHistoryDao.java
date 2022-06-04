package com.deutsche.Blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.deutsche.Blog.model.BlogHistory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BlogHistoryDao extends JpaRepository<BlogHistory, Long> {

    @Transactional
    @Modifying
    @Query("SELECT bh FROM BlogHistory bh WHERE bh.originalBlog.id = :blogId")
    List<BlogHistory> findHistoryByBlogId(long blogId);

    @Transactional
    @Modifying
    @Query("DELETE FROM BlogHistory bh WHERE bh.originalBlog.id = :blogId")
    void deleteBlog(long blogId);
}
