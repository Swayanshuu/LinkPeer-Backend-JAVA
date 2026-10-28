package com.swynx.linkpeer_backend.post_like.service;

import com.swynx.linkpeer_backend.common.exception.ResourceNotFoundException;
import com.swynx.linkpeer_backend.notifications.service.NotificationService;
import com.swynx.linkpeer_backend.post.entity.Post;
import com.swynx.linkpeer_backend.post.repository.PostRepository;
import com.swynx.linkpeer_backend.post_like.entity.PostLike;
import com.swynx.linkpeer_backend.post_like.repository.PostLikeRepository;
import org.springframework.stereotype.Service;

@Service
public class PostLikeServiceImpl implements PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final NotificationService notificationService;

    public PostLikeServiceImpl(PostLikeRepository postLikeRepository, PostLikeRepository postLikeRepository1, PostRepository postRepository, NotificationService notificationService) {
        this.postLikeRepository = postLikeRepository1;
        this.postRepository = postRepository;
        this.notificationService = notificationService;
    }

    @Override
    public void likePost(Long postId, String userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found!"));


        boolean alreadyLiked = postLikeRepository.existsByPostIdAndUserId(postId, userId);
        if (alreadyLiked) {
            return;
        }

        PostLike postLike = new PostLike(postId, userId);

        postLikeRepository.save(postLike);

        // don't notify user if they liker their own post
        if (!post.getUserId().equals(userId)) {
            notificationService.createNotification(
                    post.getUserId(), userId, postId, null, "POST_LIKE", "New Like", "Someone liked your post"
            );
        }
    }

    @Override
    public void unlikePost(Long postId, String userId) {
        postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found!"));

        boolean alreadyUnliked = postLikeRepository.existsByPostIdAndUserId(postId, userId);

        if (!alreadyUnliked) {
            return;
        }

        postLikeRepository.deleteByPostIdAndUserId(postId, userId);
    }

    @Override
    public boolean hasUserLikedPost(Long postId, String userId) {
        return postLikeRepository.existsByPostIdAndUserId(postId, userId);
    }
}
