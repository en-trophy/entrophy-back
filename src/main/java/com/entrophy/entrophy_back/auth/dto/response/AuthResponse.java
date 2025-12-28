package com.entrophy.entrophy_back.auth.dto.response;

public record AuthResponse(
        String accessToken,
        Long userId,
        String loginId,
        String name
) {
}
