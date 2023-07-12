package com.example.post.controller;

import com.example.post.dto.PostRequestDto;
import com.example.post.dto.PostResponseDto;

import com.example.post.security.UserDetailsImpl;
import com.example.post.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시물 생성 api
    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto RequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.createPost(RequestDto,userDetails);
    }

    //게시물 전체 조회 api
    @GetMapping("/posts")
    public List<PostResponseDto> getPost (){
        return postService.getPosts();
    }

    //선택한 게시물 조회 api
    @GetMapping("/postNumber/{id}")
    public PostResponseDto getPost(@PathVariable Long id){
        return postService.getPost(id);
    }

     //게시물 수정
    @PutMapping("/postNumber/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.updatePost(id,requestDto,userDetails);
    }

    //게시물 삭제
    @DeleteMapping("/postNumber/{id}")
    public ResponseEntity deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.deletePost(id,userDetails);

    }

}
