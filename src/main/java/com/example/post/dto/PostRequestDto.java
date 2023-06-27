package com.example.post.dto;

import lombok.Getter;
import lombok.Setter;

import javax.swing.text.StringContent;

@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String content;
}
