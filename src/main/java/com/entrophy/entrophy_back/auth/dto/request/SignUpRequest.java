package com.entrophy.entrophy_back.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequest(

        //사용자 아이디
        @NotBlank @Size(min = 4, max = 50)
        String loginId,

        //비번
        @NotBlank @Size(min = 8, max = 72)
        String password,

        //이름
        @NotBlank @Size(min = 1, max = 50)
        String name
) {}