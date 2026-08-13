package com.swynx.linkpeer_backend.post.service;

import com.swynx.linkpeer_backend.common.exception.ResourceNotFoundException;
import com.swynx.linkpeer_backend.post.entity.Post;
import com.swynx.linkpeer_backend.post.repository.PostRepository;
import com.swynx.linkpeer_backend.user.entity.User;
import com.swynx.linkpeer_backend.user.repository.UserRepository;
import org.springframework.stereotype.Service;

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
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }


}
