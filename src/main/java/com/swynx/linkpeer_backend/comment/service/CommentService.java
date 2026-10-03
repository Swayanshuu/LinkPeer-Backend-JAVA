package com.swynx.linkpeer_backend.comment.service;

import com.swynx.linkpeer_backend.comment.entity.Comment;

import java.util.List;

public interface CommentService {

    // create comment
    Comment createComment(String userId, Long postId, Comment comment);

    // list all comments of the post
    List<Comment> getCommentsByPostId(Long postId);

    // update comment
    Comment updateComment(String userId, Long commentId, Comment comment);

    // delete comment
    void deleteComment(String userId, Long commentId);

    // like a comment
    void likeComment(String userId, Long commentsId);

    // unlike a comment
    void unlikeComment(String userId, Long commentsId);

    // check if a user liked the comment
    boolean hasUserLikedTheComment(String userId, Long commentsId);
}
