package com.swynx.linkpeer_backend.post.service;

import com.swynx.linkpeer_backend.post.dto.request.PostUpdateRequest;
import com.swynx.linkpeer_backend.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    Post createPost(String userId, Post post);
    Post getPostById(Long id);

    // making it pageable so that we can't fetch al posts at once
    Page<Post> getAllPosts(Pageable pageable);

    Post updatePost(String userId, Long id, PostUpdateRequest request);

    void deletePost(String userId, Long id);

}
