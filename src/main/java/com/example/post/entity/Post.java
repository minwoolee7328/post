package com.example.post.entity;

import com.example.post.dto.PostRequestDto;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "password", nullable = false)
    private Long password;

    @Column(name = "content", nullable = false, length = 500)
    private String content;


    public Post(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.name = postRequestDto.getName();
        this.password = postRequestDto.getPassword();
        this.content = postRequestDto.getContent();
    }
    @Transactional
    public void update(PostRequestDto RequestDto){
        this.title = RequestDto.getTitle();
        this.name = RequestDto.getName();
        this.content = RequestDto.getContent();
    }

}
