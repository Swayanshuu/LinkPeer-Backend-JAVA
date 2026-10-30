package com.swynx.linkpeer_backend.broadcast.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name="broadcasts")
@Getter
@Setter
public class Broadcast {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String message;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "link_url")
    private String linkUrl;

    @Column(nullable = false)
    private String audience = "all";

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "total_recipients")
    private Integer totalRecipients = 0;

    @Column(name = "total_opens")
    private Integer totalOpens = 0;

    @Column(name = "click_count")
    private Integer clickCount = 0;

    @Column(name = "link_clicks")
    private Integer linkClicks = 0;


}
