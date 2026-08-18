package com.swynx.linkpeer_backend.post.service;

import com.swynx.linkpeer_backend.common.exception.ResourceNotFoundException;
import com.swynx.linkpeer_backend.post.dto.request.PostUpdateRequest;
import com.swynx.linkpeer_backend.post.entity.Post;
import com.swynx.linkpeer_backend.post.repository.PostRepository;
import com.swynx.linkpeer_backend.user.entity.User;
import com.swynx.linkpeer_backend.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostServiceImpl(
            PostRepository postRepository,
            UserRepository userRepository) {

        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Post createPost(String userId, Post post) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        post.setUserId(user.getId());
        post.setUserName(user.getName());
        post.setUserPhoto(user.getPhotoUrl());
        post.setUserType(user.getUserType());
        post.setDepartment(user.getDepartment());
        post.setBranch(user.getBranch());
        post.setDesignation(user.getDesignation());

        return postRepository.save(post);
    }

    @Override
    public Post getPostById(Long id) {
        return  postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post not found"));
    }

    @Override
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Post updatePost(String userId, Long id, PostUpdateRequest request) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post not found"));

        if(!post.getUserId().equals(userId)){
            throw new ResourceNotFoundException("You can't update this post!");
        }

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setLink(request.getLink());
        post.setImageUrl(request.getImageUrl());
        post.setFileUrl(request.getFileUrl());
        post.setFileName(request.getFileName());
        post.setFileType(request.getFileType());
        post.setImageUrls(request.getImageUrls());

        post.setUpdatedAt(LocalDateTime.now());

        return postRepository.save(post);
    }


}
