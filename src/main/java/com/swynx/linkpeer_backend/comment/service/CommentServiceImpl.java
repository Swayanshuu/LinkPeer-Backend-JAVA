package com.swynx.linkpeer_backend.comment.service;

import com.swynx.linkpeer_backend.comment.entity.Comment;
import com.swynx.linkpeer_backend.comment.repository.CommentRepository;
import com.swynx.linkpeer_backend.common.exception.ResourceNotFoundException;
import com.swynx.linkpeer_backend.post.entity.Post;
import com.swynx.linkpeer_backend.post.repository.PostRepository;
import com.swynx.linkpeer_backend.user.entity.User;
import com.swynx.linkpeer_backend.user.exception.UnauthorizedException;
import com.swynx.linkpeer_backend.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Comment createComment(String userId, Long postId, Comment comment) {
        // Check whether the post exists
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        // Get the user who is creating the comment
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Set which post this comment belongs to
        comment.setPostId(post.getId());

        // Set who created the comment
        comment.setUserId(userId);

        // Store user information with the comment
        comment.setUserName(user.getName());
        comment.setUserPhoto(user.getPhotoUrl());

        // Save the comment in the database
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByPostId(Long postId) {
        // Check whether the post exists
        postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        // Get all comments belonging to this post
        return commentRepository.findByPostId(postId);
    }

    @Override
    public Comment updateComment(String userId, Long commentId, Comment requestComment) {

        // Find the existing comment from the database
        Comment existingComment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        // Check whether the current user owns this comment
        if (!existingComment.getUserId().equals(userId)) {
            throw new UnauthorizedException("You can't update this comment");
        }

        // Update only the content
        existingComment.setContent(requestComment.getContent());

        // Save and return the updated comment
        return commentRepository.save(existingComment);
    }

    @Override
    public void deleteComment(String userId, Long commentId) {
        // Find the comment
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        // Check whether the current user owns this comment
        if (!comment.getUserId().equals(userId)) {
            throw new UnauthorizedException("You can't delete this comment");
        }

        // Delete the comment
        commentRepository.delete(comment);
    }

    @Override
    public void likeComment(String userId, Long commentsId) {
        Comment comment = commentRepository.findById(commentsId).orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        // get list if liked user
        String[] likedBy = comment.getLikedBy();

        // check if current user like the comment?
        if (likedBy != null) {
            for (String id : likedBy) {
                if (id.equals(userId)) return;
            }
        }

        // add the user to likedBy
        List<String> users = likedBy == null
                ? new ArrayList<>()
                : new ArrayList<>(Arrays.asList(likedBy));

        users.add(userId);

        // Update liked users
        comment.setLikedBy(users.toArray(new String[0]));

        // Increase like count
        comment.setLikesCount(comment.getLikesCount() + 1);

        // Save updated comment
        commentRepository.save(comment);
    }

    @Override
    public void unlikeComment(String userId, Long commentsId) {
        Comment comment = commentRepository.findById(commentsId).orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        String[] likedBy = comment.getLikedBy();
        if (likedBy != null) {
            return;
        }

        List<String> users = new ArrayList<>(Arrays.asList(likedBy));
        boolean removed = users.remove(userId);

        if (!removed) {
            return;
        }

        comment.setLikedBy(users.toArray(new String[0]));
        comment.setLikesCount(comment.getLikesCount() - 1);
        commentRepository.save(comment);
    }

    @Override
    public boolean hasUserLikedTheComment(String userId, Long commentsId) {
        Comment comment = commentRepository.findById(commentsId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Comment not found"));

        String[] likedBy = comment.getLikedBy();

        if (likedBy == null) {
            return false;
        }

        return Arrays.asList(likedBy).contains(userId);
    }
}
