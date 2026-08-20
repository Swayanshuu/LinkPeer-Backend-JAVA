package com.swynx.linkpeer_backend.post.mapper;

import com.swynx.linkpeer_backend.auth.service.FirebaseAuthService;
import com.swynx.linkpeer_backend.post.dto.request.PostCreateRequest;
import com.swynx.linkpeer_backend.post.dto.response.PostResponse;
import com.swynx.linkpeer_backend.post.dto.response.SelfPostResponse;
import com.swynx.linkpeer_backend.post.entity.Post;
import com.swynx.linkpeer_backend.post_like.repository.PostLikeRepository;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    private final PostLikeRepository postLikeRepository;

    public PostMapper(PostLikeRepository postLikeRepository) {
        this.postLikeRepository = postLikeRepository;
    }

    public PostResponse toResponse(Post post, String currentUserId) {

        PostResponse response = new PostResponse();

        response.setUserName(post.getUserName());
        response.setUserPhoto(post.getUserPhoto());
        response.setPostType(post.getPostType());
        response.setTitle(post.getTitle());
        response.setContent(post.getContent());
        response.setLink(post.getLink());
        response.setCreatedAt(post.getCreatedAt());
        response.setUserType(post.getUserType());
        response.setDepartment(post.getDepartment());
        response.setUpdatedAt(post.getUpdatedAt());
        response.setBranch(post.getBranch());
        response.setImageUrls(post.getImageUrls());

        // Count total likes for this post
        long likeCount = postLikeRepository.countByPostId(post.getId());

        // Add the count to the response
        response.setLikeCount(likeCount);

        boolean isLiked = false;

        // If a user is logged in, check whether THEY liked this post
        if (currentUserId != null) {

            isLiked = postLikeRepository.existsByPostIdAndUserId(post.getId(), currentUserId);
        }

        return response;
    }

    public SelfPostResponse toSelfResponse(Post post, String currentUserId) {

        SelfPostResponse response = new SelfPostResponse();

        response.setId(post.getId());
        response.setUserId(post.getUserId());
        response.setUserName(post.getUserName());
        response.setUserPhoto(post.getUserPhoto());
        response.setPostType(post.getPostType());
        response.setTitle(post.getTitle());
        response.setContent(post.getContent());
        response.setLink(post.getLink());
        response.setCreatedAt(post.getCreatedAt());
        response.setUserType(post.getUserType());
        response.setDepartment(post.getDepartment());
        response.setImageUrl(post.getImageUrl());
        response.setFileUrl(post.getFileUrl());
        response.setFileName(post.getFileName());
        response.setFileType(post.getFileType());
        response.setUpdatedAt(post.getUpdatedAt());
        response.setBranch(post.getBranch());
        response.setDesignation(post.getDesignation());
        response.setImageUrls(post.getImageUrls());

        // Count total likes for this post
        long likeCount = postLikeRepository.countByPostId(post.getId());

        // By default, the current user has not liked the post
        boolean isLiked = false;

        // If a user is logged in, check whether THEY liked this post
        if (currentUserId != null) {

            isLiked = postLikeRepository.existsByPostIdAndUserId(post.getId(), currentUserId);
        }

        response.setLikedByCurrentUser(isLiked);

        return response;
    }

    public Post toEntity(PostCreateRequest request) {

        Post post = new Post();

        post.setPostType(request.getPostType());
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setLink(request.getLink());
        post.setImageUrl(request.getImageUrl());
        post.setFileUrl(request.getFileUrl());
        post.setFileName(request.getFileName());
        post.setFileType(request.getFileType());
        post.setImageUrls(request.getImageUrls());

        return post;
    }
}