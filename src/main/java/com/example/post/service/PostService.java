package com.example.post.service;

import com.example.post.comment.entity.Comment;
import com.example.post.comment.repository.CommentRepository;
import com.example.post.dto.PostRequestDto;
import com.example.post.dto.PostResponseDto;
import com.example.post.entity.Post;
import com.example.post.repository.PostRepository;
import com.example.post.security.UserDetailsImpl;
import com.example.post.user.dto.EnumDto;
import com.example.post.user.entity.StatusEnum;
import com.example.post.user.entity.User;
import com.example.post.user.entity.UserRoleEnum;
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
    private final CommentRepository commentRepository;
    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }
    //게시글 생성
    public PostResponseDto createPost(PostRequestDto RequestDto, UserDetailsImpl userDetails) {

        User user = userDetails.getUser();

        Post post = new Post(RequestDto,user);

        postRepository.save(post);

        PostResponseDto postResponseDto = new PostResponseDto(post);

        return postResponseDto;

    }

    //전체글 조회
    public List<PostResponseDto> getPosts(){
        List<Post> getPost = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostResponseDto> showPosts = new ArrayList<>();
        List<String> comments = new ArrayList<>();

        for(Post post : getPost){
            List<Comment> commentsList = post.getCommentList();

            for( Comment comment :commentsList){
                comments.add(comment.getComment());
            }
            showPosts.add(new PostResponseDto(post,comments));
        }
        return showPosts;
    }

    //선택한 게시글 조회
    public PostResponseDto getPost(Long id) {

        Post post = findPost(id);

        List<String> comments = new ArrayList<>();
        List<Comment> commentsList = post.getCommentList();

        for( Comment comment :commentsList){
            comments.add(comment.getComment());
        }

        return new PostResponseDto(post,comments);

    }
    //게시글 수정
    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        Post post = findPost(id);

        // 해당글의 작성자 와 토큰의 사용자가 같으면 업데이트
        if(post.getUser().getUsername().equals(user.getUsername())|| user.getRole().equals(UserRoleEnum.ADMIN)){
            post.update(requestDto);
            return new PostResponseDto(post);
        }else {
            throw new IllegalArgumentException("작성자가 다릅니다.");
        }

    }

    public ResponseEntity deletePost(Long id, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        Post post = findPost(id);

       //  해당글의 작성자 와 토큰의 사용자가 같으면 삭제
        if(post.getUser().getUsername().equals(user.getUsername())|| user.getRole().equals(UserRoleEnum.ADMIN)){

            // 선택한 게시글의 댓글을 먼저 삭제
            List<Comment> commentsList = post.getCommentList();
            for( Comment comment :commentsList){
                System.out.println("comment = " + comment);
                commentRepository.delete(comment);
            }

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
