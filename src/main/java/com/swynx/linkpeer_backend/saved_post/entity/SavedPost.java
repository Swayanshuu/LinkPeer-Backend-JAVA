package com.swynx.linkpeer_backend.saved_post.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="saved_posts")
@IdClass(SavedPostId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SavedPost {

    @Id
    @Column(name="post_id")
    private Long postId;

    @Id
    @Column(name="user_id")
    private String userId;

    @Column(name="created_at")
    private LocalDateTime createdAt;
}
