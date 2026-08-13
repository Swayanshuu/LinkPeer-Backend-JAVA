package com.swynx.linkpeer_backend.post.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_photo")
    private String userPhoto;

    @Column(name = "post_type", nullable = false)
    private String postType;

    private String title;

    @Column(nullable = false)
    private String content;

    private String link;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "user_type")
    private String userType;

    private String department;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    private String branch;

    private String designation;

    @Column(name = "image_urls", columnDefinition = "text[]")
    private List<String> imageUrls;
}
