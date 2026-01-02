package com.entrophy.entrophy_back.user.controller;


import com.entrophy.entrophy_back.user.dto.response.UserResponse;
import com.entrophy.entrophy_back.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원 API",description = "회원 정보 조회, 삭제 기능 제공 ")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    //유저 정보 조회
    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    @Operation( summary = "유저 정보 조회",  description = "유저 id로 유저 정보를 조회함")
    public ResponseEntity<UserResponse> getUserInfo(@Parameter @RequestParam Long id){
        return ResponseEntity.ok(userService.getUser(id));
    }


}
