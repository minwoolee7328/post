package com.example.post.like.controller;

import com.example.post.like.entity.postLike;
import com.example.post.like.service.likeService;
import com.example.post.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class likeController {

    private final likeService likeService;

    public likeController(likeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/post/{id}/like")
    public boolean checkLike(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
      return likeService.checkLike(id, userDetails);
    }

    @PutMapping("/post/{id}/ch")
    public boolean chLike(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likeService.chLike(id, userDetails);
    }

}
