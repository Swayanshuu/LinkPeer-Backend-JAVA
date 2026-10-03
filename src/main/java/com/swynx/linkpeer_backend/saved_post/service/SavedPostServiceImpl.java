package com.swynx.linkpeer_backend.saved_post.service;

import com.swynx.linkpeer_backend.common.exception.ResourceNotFoundException;
import com.swynx.linkpeer_backend.post.entity.Post;
import com.swynx.linkpeer_backend.post.repository.PostRepository;
import com.swynx.linkpeer_backend.saved_post.entity.SavedPost;
import com.swynx.linkpeer_backend.saved_post.repository.SavedPostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SavedPostServiceImpl implements SavedPostService {

    private final SavedPostRepository savedPostRepository;
    private final PostRepository postRepository;

    public SavedPostServiceImpl(
            SavedPostRepository savedPostRepository,
            PostRepository postRepository
    ) {
        this.savedPostRepository = savedPostRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void savePost(String userId, Long postId) {

        // Make sure the post exists
        postRepository.findById(postId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Post not found"));

        // Don't save the same post twice
        boolean alreadySaved =
                savedPostRepository.existsByPostIdAndUserId(
                        postId,
                        userId
                );

        if (alreadySaved) {
            return;
        }

        // Create saved-post record
        SavedPost savedPost = new SavedPost();

        savedPost.setPostId(postId);
        savedPost.setUserId(userId);
        savedPost.setCreatedAt(LocalDateTime.now());

        savedPostRepository.save(savedPost);
    }

    @Override
    public void unsavePost(String userId, Long postId) {

        // Make sure the post exists
        postRepository.findById(postId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Post not found"));

        // If it isn't saved, nothing to remove
        boolean saved =
                savedPostRepository.existsByPostIdAndUserId(
                        postId,
                        userId
                );

        if (!saved) {
            return;
        }

        savedPostRepository.deleteByPostIdAndUserId(
                postId,
                userId
        );
    }

    @Override
    public boolean isPostSaved(String userId, Long postId) {

        // Make sure the post exists
        postRepository.findById(postId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Post not found"));

        return savedPostRepository.existsByPostIdAndUserId(
                postId,
                userId
        );
    }
}