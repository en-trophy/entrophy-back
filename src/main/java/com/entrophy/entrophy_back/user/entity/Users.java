package com.entrophy.entrophy_back.user.entity;


import com.entrophy.entrophy_back.auth.dto.request.SignUpRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;

    private String password;

    private String name;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    protected Users(){}

    public Users(SignUpRequest request, String encodedPassword) {
        this.loginId = request.loginId();
        this.password = encodedPassword;
        this.name = request.name();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }


}
