package com.example.post.commentLike.service;

import com.example.post.comment.entity.Comment;
import com.example.post.comment.repository.CommentRepository;
import com.example.post.commentLike.entity.CommentLike;
import com.example.post.commentLike.repository.CommentLikeRepository;
import com.example.post.entity.Post;
import com.example.post.security.UserDetailsImpl;
import com.example.post.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;

    public CommentLikeService(CommentLikeRepository commentLikeRepository, CommentRepository commentRepository) {
        this.commentLikeRepository = commentLikeRepository;
        this.commentRepository = commentRepository;
    }

    public boolean likeCheck(Long id, UserDetailsImpl userDetails) {
        // 커멘트 데이터와 유저데이터를 합친 데이터 생성

        Comment comment =  findPost(id);

        User user = userDetails.getUser();

        // 데이터가 있는지 없는지 확인
        Optional<CommentLike> commentLikes = commentLikeRepository.findByComment_idAndUser_id(comment.getId(), user.getId());


        if(commentLikes.isEmpty()){

            CommentLike commentLike = new CommentLike(user,comment);
            commentLikeRepository.save(commentLike);

            return commentLike.isLikechek();
        }


        return commentLikes.get().isLikechek();
    }

    @Transactional
    public boolean chLike(Long id, UserDetailsImpl userDetails) {
        // 커멘트 데이터와 유저데이터를 합친 데이터 생성

        Comment comment =  findPost(id);

        User user = userDetails.getUser();

        // 데이터가 있는지 없는지 확인
        Optional<CommentLike> commentLikes = commentLikeRepository.findByComment_idAndUser_id(comment.getId(), user.getId());

        if(commentLikes.get().isLikechek()){
            // ture인 상태
            commentLikes.get().setLikechek(false);

            commentLikeRepository.save(commentLikes.get());
        }else{
            // false인 상태
            commentLikes.get().setLikechek(true);

            commentLikeRepository.save(commentLikes.get());
        }

        return commentLikes.get().isLikechek();
    }

    private Comment findPost(Long id){
        return commentRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }

}
