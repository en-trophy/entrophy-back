package com.entrophy.entrophy_back.user.service;

import com.entrophy.entrophy_back.user.dto.response.UserResponse;
import com.entrophy.entrophy_back.user.entity.Users;
import com.entrophy.entrophy_back.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    //유저 정보 조회
    public UserResponse getUser(Long id){
        Users users = userRepository.findById(id).orElseThrow(()->new NoSuchElementException("해당 아이디의 유저 없음"));
        return toResponseDto(users);
    }

    private UserResponse toResponseDto (Users users){
        return new UserResponse(
                users.getId(),
                users.getLoginId(),
                users.getName(),
                users.getCreatedAt(),
                users.getUpdatedAt()
        );
    }

}
