package com.swynx.linkpeer_backend.post_like.service;

import com.swynx.linkpeer_backend.common.exception.ResourceNotFoundException;
import com.swynx.linkpeer_backend.post.entity.Post;
import com.swynx.linkpeer_backend.post.repository.PostRepository;
import com.swynx.linkpeer_backend.post_like.entity.PostLike;
import com.swynx.linkpeer_backend.post_like.repository.PostLikeRepository;

public class PostLikeServiceImpl implements PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    public PostLikeServiceImpl(PostLikeRepository postLikeRepository, PostLikeRepository postLikeRepository1, PostRepository postRepository) {
        this.postLikeRepository = postLikeRepository1;
        this.postRepository = postRepository;
    }

    @Override
    public void likePost(Long postId, String userId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post not found!"));


        boolean alreadyLiked = postLikeRepository.existsByPostIdAndUserId(postId, userId);
        if (alreadyLiked) {return;}

        PostLike postLike = new PostLike(postId, userId);

        postLikeRepository.save(postLike);
    }

    @Override
    public void unlikePost(Long postId, String userId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post not found!"));

        boolean alreadyUnliked = postLikeRepository.existsByPostIdAndUserId(postId, userId);

        if (!alreadyUnliked) {return;}

        postLikeRepository.deleteByPostIdAndUserId(postId, userId);
    }

    @Override
    public boolean hasUserLikedPost(Long postId, String userId) {
        return postLikeRepository.existsByPostIdAndUserId(postId, userId);
    }
}
