package com.example.post.dto;

import lombok.Getter;

import javax.swing.text.StringContent;

@Getter
public class PostRequestDto {
    private String title;
    private String name;
    private Long password;
    private String content;
}
