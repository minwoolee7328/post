package com.example.post.comment.service;

import com.example.post.comment.dto.CommentRequestDto;
import com.example.post.comment.dto.CommentResponseDto;
import com.example.post.comment.entity.Comment;
import com.example.post.comment.repository.CommentRepository;
import com.example.post.entity.Post;
import com.example.post.repository.PostRepository;
import com.example.post.user.dto.EnumDto;
import com.example.post.user.entity.StatusEnum;
import com.example.post.user.entity.User;
import com.example.post.user.entity.UserRoleEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public CommentResponseDto createComment(CommentRequestDto requestDto, HttpServletRequest req) {
        User user = (User) req.getAttribute("user");
        Post post = findPost(requestDto.getId());

        Comment comment = new Comment(requestDto,post,user);

        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }
    @Transactional
    public CommentResponseDto updateCommemt(Long id, CommentRequestDto requestDto, HttpServletRequest req) {
        User user = (User) req.getAttribute("user");

        Comment findComment = findComment(id);

        // 관리자계정이거나 게시글을 작성한사람인지 확인
        if(findComment.getUser().getUsername().equals(user.getUsername()) || user.getRole().equals(UserRoleEnum.ADMIN)){
            findComment.update(requestDto);
            return new CommentResponseDto(findComment);
        }else {
            throw new IllegalArgumentException("작성자가 다릅니다.");
        }

    }

    public ResponseEntity deleteComment(Long id, HttpServletRequest req) {
        User user = (User) req.getAttribute("user");

        Comment findComment = findComment(id);

        // 관리자계정이거나 게시글을 작성한사람인지 확인
        if(findComment.getUser().getUsername().equals(user.getUsername()) || user.getRole().equals(UserRoleEnum.ADMIN)){
           commentRepository.delete(findComment);
            EnumDto SuccessEnumDto = new EnumDto(StatusEnum.OK,"삭제가 완료되었습니다.");
            return new ResponseEntity(SuccessEnumDto, HttpStatus.OK);
        }else {
            throw new IllegalArgumentException("작성자가 다릅니다.");
        }
    }

    private Post findPost(Long id){
        return postRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }

    private Comment findComment(Long id){
        return commentRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }



}
