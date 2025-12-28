package com.entrophy.entrophy_back.auth.service;

import com.entrophy.entrophy_back.auth.dto.request.LoginRequest;
import com.entrophy.entrophy_back.auth.dto.request.SignUpRequest;
import com.entrophy.entrophy_back.auth.dto.response.AuthResponse;
import com.entrophy.entrophy_back.global.security.JwtProvider;
import com.entrophy.entrophy_back.user.entity.Users;
import com.entrophy.entrophy_back.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    //회원가입
    public void signUp(SignUpRequest request) {

        //유저 아이디 조회
        if (userRepository.existsByLoginId(request.loginId())) {
            throw new IllegalArgumentException("이미 존재하는 아이디임");
        }

        String encodedPassword = passwordEncoder.encode(request.password());

        Users user = new Users(request, encodedPassword);

        userRepository.save(user);
    }

    //로그인
    public AuthResponse login(LoginRequest request) {

        //유저 아이디 조회
        Users user = userRepository.findByLoginId(request.loginId())
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디 없음 "));

        //비번 검사
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new IllegalArgumentException("비번 틀림");
        }

        //토근 발급
        String token = jwtProvider.createAccessToken(user.getId(), user.getLoginId());

        return new AuthResponse(
                token,
                user.getId(),
                user.getLoginId(),
                user.getName()
        );
    }
}
