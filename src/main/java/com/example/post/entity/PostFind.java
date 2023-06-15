package com.example.post.entity;

import java.time.LocalDateTime;

public interface PostFind {
    Long getId();
    String getContent();
    String getName();
    String getTitle();
    LocalDateTime getCreatedAt();
}
