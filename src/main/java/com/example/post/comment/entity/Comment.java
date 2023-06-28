package com.example.post.comment.entity;

import com.example.post.comment.dto.CommentRequestDto;
import com.example.post.entity.Post;
import com.example.post.entity.Timestamped;
import com.example.post.user.entity.User;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comment")
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment", nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Comment(CommentRequestDto commentRequestDto, Post post, User user){
        this.comment = commentRequestDto.getComment();
        this.post = post;
        this.user = user;
    }

    @Transactional
    public void update(CommentRequestDto requestDto) {
        this.comment = requestDto.getComment();
    }
}
