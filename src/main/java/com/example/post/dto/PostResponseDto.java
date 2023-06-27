package com.example.post.dto;

import com.example.post.entity.Post;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponseDto {
    private boolean success;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.writer = post.getUser().getUsername();
        this.createdAt = post.getCreatedAt();
    }

    public PostResponseDto(boolean success){
        this.success = success;
    }
}
