package com.example.post.user.controller;

import com.example.post.user.dto.UserRequestDto;
import com.example.post.user.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //회원 가입
    @PostMapping("/signup")
    public ResponseEntity UserSignUp(@RequestBody @Valid UserRequestDto requestDto, BindingResult bindingResult){
       return userService.UserSignUp(requestDto,bindingResult);
    }



}
