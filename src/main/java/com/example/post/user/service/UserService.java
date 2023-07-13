package com.example.post.user.service;



import com.example.post.jwt.JwtUtil;
import com.example.post.user.dto.EnumDto;
import com.example.post.user.dto.LoginRequestDto;
import com.example.post.user.dto.SignupRequestDto;
import com.example.post.user.entity.StatusEnum;
import com.example.post.user.entity.User;
import com.example.post.user.entity.UserRoleEnum;
import com.example.post.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public ResponseEntity UserSignUp(SignupRequestDto requestDto, BindingResult bindingResult) {
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            for(FieldError fieldError : bindingResult.getFieldErrors())
            {
                log.error(fieldError.getField() + "필드 :" + fieldError.getDefaultMessage());
            }

            // 표현식과 다르게 입력했을때 status 400 / false
            EnumDto ValidationEnumDto = new EnumDto(StatusEnum.BAD_REQUEST,"잘못 입력하셨습니다.");
            return new ResponseEntity(ValidationEnumDto, HttpStatus.BAD_REQUEST);
        }

        // 회원가입 데이터가 디비데이터와 중복 되는지 확인.
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        Optional<User> checkUsername = userRepository.findByUsername(username);

        if (checkUsername.isPresent()) {
            // username이 중복될때
            EnumDto ValidationEnumDto = new EnumDto(StatusEnum.BAD_REQUEST,"중복된 유저가 있습니다.");
            return new ResponseEntity(ValidationEnumDto, HttpStatus.BAD_REQUEST);
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (StringUtils.hasText(requestDto.getAdminToken())) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        // 중복이 없을때 저장
        User userData = new User(username, password, role);
        userRepository.save(userData);

        // 표현식에 맞게 입력하고 중복된 username도 없을때 status 200 / success
        EnumDto enumDto = new EnumDto(StatusEnum.OK,"회원가입 성공");
        return new ResponseEntity(enumDto,HttpStatus.OK);
    }

}
