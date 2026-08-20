package com.swynx.linkpeer_backend.post_like.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "post_likes")
@IdClass(PostLikeId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostLike {

    /*
     * Part 1 of the composite primary key.
     * Represents post_id in the database.
     */
    @Id
    @Column(name = "post_id")
    private Long postId;


    /*
     * Part 2 of the composite primary key.
     * Represents user_id in the database.
     */
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public PostLike(Long postId, String userId) {
        this.postId = postId;
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
    }
}