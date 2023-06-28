package com.example.post.user.dto;

import com.example.post.user.entity.UserRoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    @Pattern(regexp = "^[a-z0-9]{4,10}")
    @NotBlank
    private String username;
    @Pattern(regexp = "^(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,15}")
    @NotBlank
    private String password;
    private String adminToken = "";

}
