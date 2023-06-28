package com.example.post.comment.controller;

import com.example.post.comment.dto.CommentRequestDto;
import com.example.post.comment.dto.CommentResponseDto;
import com.example.post.comment.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment")
    public CommentResponseDto createComment(@RequestBody CommentRequestDto requestDto, HttpServletRequest req){
        return commentService.createComment(requestDto,req);
    }

    @PutMapping("/comment/{id}")
    public CommentResponseDto updateCommemt(@PathVariable Long id,@RequestBody CommentRequestDto requestDto ,HttpServletRequest req){
        return commentService.updateCommemt(id,requestDto,req);
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity deleteComment(@PathVariable Long id, HttpServletRequest req){
        return commentService.deleteComment(id, req);
    }


}
