package com.swynx.linkpeer_backend.comment.repository;

import com.swynx.linkpeer_backend.comment.entity.Comment;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>
{
    // get all comments of a post
    List<Comment> findByPostId(Long postId);

    // count total comments of a post
    long countByPostId(Long postId);
}
