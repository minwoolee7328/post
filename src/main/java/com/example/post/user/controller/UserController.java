package com.example.post.user.controller;

import com.example.post.user.dto.LoginRequestDto;
import com.example.post.user.dto.SignupRequestDto;
import com.example.post.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //회원 가입
    @PostMapping("/user/signup")
    public ResponseEntity UserSignUp(@RequestBody @Valid SignupRequestDto requestDto, BindingResult bindingResult){
       return userService.UserSignUp(requestDto,bindingResult);
    }

}
