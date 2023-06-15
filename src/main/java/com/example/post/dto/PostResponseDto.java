package com.example.post.dto;

import com.example.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private String title;
    private String name;
    private String content;
    private LocalDateTime createdAt;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.name = post.getName();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
    }
}
