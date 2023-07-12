package com.example.post.like.repository;

import com.example.post.like.entity.postLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface likeRepository extends JpaRepository<postLike,Long> {
    Optional<postLike> findByPost_idAndUser_id(Long post, Long user);

    List<postLike> findByPost_idAndLikechek(Long id, boolean check);
}
