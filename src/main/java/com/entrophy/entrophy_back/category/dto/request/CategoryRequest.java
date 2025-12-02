package com.entrophy.entrophy_back.category.dto.request;

public record CategoryRequest(

        String code,

        String name,

        String iconEmoji,

        String description
) {
}
