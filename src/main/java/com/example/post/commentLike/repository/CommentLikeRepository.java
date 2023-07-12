package com.example.post.commentLike.repository;

import com.example.post.comment.entity.Comment;
import com.example.post.commentLike.entity.CommentLike;
import com.example.post.like.entity.postLike;
import com.example.post.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike,Long> {
    Optional<CommentLike> findByComment_idAndUser_id(Long comment, Long user);

    List<CommentLike> findByComment_idAndLikechek(Long id, boolean check);
}
