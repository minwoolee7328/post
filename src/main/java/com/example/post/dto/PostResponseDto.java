package com.example.post.dto;

import com.example.post.comment.entity.Comment;
import com.example.post.entity.Post;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponseDto {
    private boolean success;
    private String title;
    private String content;
    private String writer;
    private int likeNumber;
    private LocalDateTime createdAt;
    private String commentList;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.writer = post.getUser().getUsername();
        this.createdAt = post.getCreatedAt();
    }

    public PostResponseDto(Post post, List<String> comments, int likeNumber) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.writer = post.getUser().getUsername();
        this.createdAt = post.getCreatedAt();
        this.commentList = comments.toString();
        this.likeNumber = likeNumber;
    }

    public PostResponseDto(boolean success){
        this.success = success;
    }
}
