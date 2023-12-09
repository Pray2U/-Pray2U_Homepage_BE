package com.pray2you.p2uhomepage.domain.user.dto.response;

import com.pray2you.p2uhomepage.domain.user.entity.User;
import lombok.Getter;

@Getter
public class SimpleUserInfoResponseDTO {
    private final long writerId;
    private final String writerName;
    private final String writerProfileImg;

    public SimpleUserInfoResponseDTO(long writerId, String writerName, String writerProfileImg) {
        this.writerId = writerId;
        this.writerName = writerName;
        this.writerProfileImg = writerProfileImg;
    }

    public static SimpleUserInfoResponseDTO toDTO(User user) {
        return new SimpleUserInfoResponseDTO(user.getId(), user.getUsername(), user.getProfileImgUrl());
    }
}