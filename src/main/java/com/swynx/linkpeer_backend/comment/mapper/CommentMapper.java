package com.swynx.linkpeer_backend.comment.mapper;

import com.swynx.linkpeer_backend.comment.dto.request.CommentCreateRequest;
import com.swynx.linkpeer_backend.comment.dto.response.CommentResponse;
import com.swynx.linkpeer_backend.comment.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    // Convert Comment entity → CommentResponse
    public CommentResponse toResponse(Comment comment) {

        CommentResponse response = new CommentResponse();

        response.setId(comment.getId());
        response.setUserName(comment.getUserName());
        response.setUserPhoto(comment.getUserPhoto());
        response.setContent(comment.getContent());
        response.setCreatedAt(comment.getCreatedAt());
        response.setUpdatedAt(comment.getUpdatedAt());

        return response;
    }

    // Convert CommentCreateRequest → Comment entity
    public Comment toEntity(CommentCreateRequest request) {

        Comment comment = new Comment();

        comment.setContent(request.getContent());

        return comment;
    }
}