package com.swynx.linkpeer_backend.post_like.repository;

import com.swynx.linkpeer_backend.post_like.entity.PostLike;
import com.swynx.linkpeer_backend.post_like.entity.PostLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, PostLikeId> {

    // Check if a specific user has already liked a specific post
    boolean existsByPostIdAndUserId(Long postId, String userId);

    // Delete the like of a specific user from a specific post
    void deleteByPostIdAndUserId(Long postId, String userId);
}
