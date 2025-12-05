package com.entrophy.entrophy_back.category.dto.response;

public record CategoryResponse(

        Integer id,

        String code,

        String name,

        String iconEmoji,

        String description
) { }
