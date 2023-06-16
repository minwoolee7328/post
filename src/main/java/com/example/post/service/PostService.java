package com.example.post.service;

import com.example.post.dto.PostRequestDto;
import com.example.post.dto.PostResponseDto;
import com.example.post.entity.Post;
import com.example.post.entity.PostFind;
import com.example.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    //게시글 생성
    public PostResponseDto createPost(PostRequestDto RequestDto) {
        Post post = new Post(RequestDto);

        postRepository.save(post);

        PostResponseDto postResponseDto = new PostResponseDto(post);

        return postResponseDto;

    }

    //전체글 조회
    public List<PostFind> getPosts(){
        return postRepository.findPosts();
    }

    //선택한 게시글 조회
    public List<PostFind> getChicePost(Long id) {

        Post post = findPost(id);

        List<PostFind> postFinds = postRepository.findCoicePosts(id);

        return postFinds;

    }
    //게시글 수정
    @Transactional
    public Post updatePost(Long id, PostRequestDto requestDto) {

        Post post = findPost(id);

        if(requestDto.getPassword() == post.getPassword()){
            post.update(requestDto);
            return post;
        }else {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

    }

    public String deletePost(Long id, PostRequestDto requestDto) {
        Post post = findPost(id);

        if(requestDto.getPassword() == post.getPassword()){
            postRepository.delete(post);
            return "Success Delete";
        }else {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
    }

    private Post findPost(Long id){
        return postRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }
}
