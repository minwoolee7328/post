package com.example.post.service;

import com.example.post.dto.PostRequestDto;
import com.example.post.dto.PostResponseDto;
import com.example.post.entity.Post;
import com.example.post.repository.PostRepository;
import com.example.post.user.dto.EnumDto;
import com.example.post.user.entity.StatusEnum;
import com.example.post.user.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    //게시글 생성
    public PostResponseDto createPost(PostRequestDto RequestDto, HttpServletRequest req) {
        User user = (User) req.getAttribute("user");

        Post post = new Post(RequestDto,user);

        postRepository.save(post);

        PostResponseDto postResponseDto = new PostResponseDto(post);

        return postResponseDto;

    }

    //전체글 조회
    public List<PostResponseDto> getPosts(){
        List<Post> getPost = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostResponseDto> showPosts = new ArrayList<>();
        for(Post post : getPost){
            showPosts.add(new PostResponseDto(post));
        }
        return showPosts;
    }

    //선택한 게시글 조회
    public PostResponseDto getPost(Long id) {

        Post post = findPost(id);

        return new PostResponseDto(post);

    }
    //게시글 수정
    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto, HttpServletRequest req) {
        User user = (User) req.getAttribute("user");
        Post post = findPost(id);

        // 해당글의 작성자 와 토큰의 사용자가 같으면 업데이트
        if(post.getUser().getUsername().equals(user.getUsername())){
            post.update(requestDto);
            return new PostResponseDto(post);
        }else {
            throw new IllegalArgumentException("작성자가 다릅니다.");
        }

    }

    public ResponseEntity deletePost(Long id, PostRequestDto requestDto, HttpServletRequest req) {
        User user = (User) req.getAttribute("user");
        Post post = findPost(id);

        // 해당글의 작성자 와 토큰의 사용자가 같으면 삭제
        if(post.getUser().getUsername().equals(user.getUsername())){
            postRepository.delete(post);
            EnumDto SuccessEnumDto = new EnumDto(StatusEnum.OK,"삭제가 완료되었습니다.");
            return new ResponseEntity(SuccessEnumDto, HttpStatus.OK);
        }else {
            EnumDto SuccessEnumDto = new EnumDto(StatusEnum.BAD_REQUEST,"작성자가 다릅니다.");
            return new ResponseEntity(SuccessEnumDto, HttpStatus.BAD_REQUEST);
        }
    }

    private Post findPost(Long id){
        return postRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }
}
