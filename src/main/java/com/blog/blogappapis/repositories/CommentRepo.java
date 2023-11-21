package com.blog.blogappapis.repositories;

import com.blog.blogappapis.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
    Page<Comment> findByPostId(Integer postId, Pageable pageable);
}
