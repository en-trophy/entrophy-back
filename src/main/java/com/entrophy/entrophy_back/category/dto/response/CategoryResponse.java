package com.entrophy.entrophy_back.category.dto.response;

public record CategoryResponse(

        Long id,

        String code,

        String name,

        String iconEmoji,

        String description
) { }
