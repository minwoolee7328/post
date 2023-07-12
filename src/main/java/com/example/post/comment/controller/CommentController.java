package com.example.post.comment.controller;

import com.example.post.comment.dto.CommentRequestDto;
import com.example.post.comment.dto.CommentResponseDto;
import com.example.post.comment.service.CommentService;
import com.example.post.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment")
    public CommentResponseDto createComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createComment(requestDto,userDetails);
    }

    @PutMapping("/comment/{id}")
    public CommentResponseDto updateCommemt(@PathVariable Long id,@RequestBody CommentRequestDto requestDto ,@AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.updateCommemt(id,requestDto,userDetails);
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.deleteComment(id, userDetails);
    }


}
