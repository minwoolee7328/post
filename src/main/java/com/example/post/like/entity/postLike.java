package com.example.post.like.entity;

import com.example.post.entity.Post;
import com.example.post.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "postlike")
@NoArgsConstructor
public class postLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="likechek", nullable = false)
    private boolean likechek;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name ="post_id")
    private Post post;

    public postLike(User user, Post post){
        this.likechek = true;
        this.user = user;
        this.post = post;
    }

}