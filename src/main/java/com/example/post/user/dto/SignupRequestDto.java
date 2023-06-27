package com.example.post.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignupRequestDto {

    @Pattern(regexp = "^[a-z0-9]{4,10}")
    @NotBlank
    private String username;
    @Pattern(regexp = "^[A-Za-z0-9]{8,15}")
    @NotBlank
    private String password;


}
