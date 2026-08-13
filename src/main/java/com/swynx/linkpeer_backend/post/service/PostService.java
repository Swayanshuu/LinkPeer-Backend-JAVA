package com.swynx.linkpeer_backend.post.service;

import com.swynx.linkpeer_backend.post.entity.Post;

import java.util.List;

public interface PostService {
    Post createPost(String userId, Post post);
    Post getPostById(Long id);

    List<Post> getAllPosts();

}
