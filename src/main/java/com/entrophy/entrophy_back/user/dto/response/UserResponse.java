package com.entrophy.entrophy_back.user.dto.response;

import java.time.LocalDateTime;

public record UserResponse(

         Long id,

         String loginId,

         String password,

         String name,

         LocalDateTime createdAt,

         LocalDateTime updatedAt
) {
}
