package com.example.post.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDto {
    private boolean success;
    private String username;
    private String password;

    public UserResponseDto(){

    }

    public UserResponseDto(boolean success){
        this.success = success;
    }

}
