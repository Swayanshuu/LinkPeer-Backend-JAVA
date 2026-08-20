package com.swynx.linkpeer_backend.post_like.service;

public interface PostLikeService {

    void likePost(Long postId, String userId);

    void unlikePost(Long postId, String userId);

    boolean hasUserLikedPost(Long postId, String userId);
}