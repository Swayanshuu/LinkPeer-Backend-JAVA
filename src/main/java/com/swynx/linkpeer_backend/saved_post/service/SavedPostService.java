package com.swynx.linkpeer_backend.saved_post.service;

public interface SavedPostService {

    void savePost(String userId, Long postId);
    void unsavePost(String userId, Long postId);
    boolean isPostSaved(String userId, Long postId);
}
