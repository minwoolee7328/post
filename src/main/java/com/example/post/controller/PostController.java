package com.example.post.controller;

import com.example.post.dto.PostRequestDto;
import com.example.post.dto.PostResponseDto;

import com.example.post.service.PostService;
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
    public PostResponseDto createPost(@RequestBody PostRequestDto RequestDto){
        return postService.createPost(RequestDto);
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
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        return postService.updatePost(id,requestDto);
    }

    //게시물 삭제
    @DeleteMapping("/postNumber/{id}")
    public PostResponseDto deletePost(@PathVariable Long id,@RequestBody PostRequestDto requestDto){
        postService.deletePost(id,requestDto);
        return new PostResponseDto(true);
    }

}
