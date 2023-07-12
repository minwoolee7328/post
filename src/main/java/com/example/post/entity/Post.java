package com.example.post.entity;

import com.example.post.comment.entity.Comment;
import com.example.post.dto.PostRequestDto;
import com.example.post.user.entity.User;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "post")
@NoArgsConstructor
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false, length = 500)
    private String content;

    @Column(name = "likeNumber")
    private int likeNumber;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();

    public Post(PostRequestDto postRequestDto,User user) {
        this.likeNumber = 0;
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.user = user;
    }
    @Transactional
    public void update(PostRequestDto RequestDto){
        this.title = RequestDto.getTitle();
        this.content = RequestDto.getContent();
    }


}
