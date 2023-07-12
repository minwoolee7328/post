package com.example.post.commentLike.controller;

import com.example.post.commentLike.service.CommentLikeService;
import com.example.post.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    public CommentLikeController(CommentLikeService commentLikeService) {
        this.commentLikeService = commentLikeService;
    }

    @PostMapping("/comment/{id}/like")
    private boolean likeCheck(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentLikeService.likeCheck(id,userDetails);
    }

    @PutMapping("/comment/{id}/ch")
    public boolean chLike(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentLikeService.chLike(id, userDetails);
    }

}
