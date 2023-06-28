package com.example.post.comment.dto;

import com.example.post.comment.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto {
    private String username;
    private String comment;
    private LocalDateTime createdAt;

    public CommentResponseDto(Comment comment){
        this.username = comment.getUser().getUsername();
        this.comment = comment.getComment();
        this.createdAt = comment.getCreatedAt();
    }
}
