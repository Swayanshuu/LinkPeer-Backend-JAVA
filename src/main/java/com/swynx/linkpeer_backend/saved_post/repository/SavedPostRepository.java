package com.swynx.linkpeer_backend.saved_post.repository;

import com.swynx.linkpeer_backend.saved_post.entity.SavedPost;
import com.swynx.linkpeer_backend.saved_post.entity.SavedPostId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavedPostRepository extends JpaRepository<SavedPost, SavedPostId> {

    void deleteByPostIdAndUserId(Long postId, String userId);

    boolean existsByPostIdAndUserId(Long postId, String userId);
}
