package com.example.post.commentLike.entity;

import com.example.post.comment.entity.Comment;
import com.example.post.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name = "commentLike")
@NoArgsConstructor
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="likeChek", nullable = false)
    private boolean likechek;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name ="comment_id")
    private Comment comment;

    public CommentLike(User user, Comment comment){
        this.likechek = true;
        this.user = user;
        this.comment = comment;
    }

}
