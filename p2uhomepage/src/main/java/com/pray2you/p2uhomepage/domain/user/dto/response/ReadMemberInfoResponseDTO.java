package com.pray2you.p2uhomepage.domain.user.dto.response;

import com.pray2you.p2uhomepage.domain.user.entity.User;
import com.pray2you.p2uhomepage.domain.user.enumeration.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
public class ReadMemberInfoResponseDTO {
    private final Long userId;
    private final String githubId;
    private final String username;
    private final String profileImgUrl;
    private final String email;
    private final Role role;
    private final LocalDateTime createdDate;

    @Builder
    private ReadMemberInfoResponseDTO(
            @NonNull Long userId,
            @NonNull String githubId,
            @NonNull String username,
            @NonNull String profileImgUrl,
            @NonNull String email,
            @NonNull Role role,
            @NonNull LocalDateTime createdDate) {
        this.userId = userId;
        this.githubId = githubId;
        this.username = username;
        this.profileImgUrl = profileImgUrl;
        this.email = email;
        this.role = role;
        this.createdDate = createdDate;
    }

    public static ReadMemberInfoResponseDTO toDTO(User user){
        return builder()
                .userId(user.getId())
                .githubId(user.getGithubId())
                .username(user.getUsername())
                .profileImgUrl(user.getProfileImgUrl())
                .email(user.getEmail())
                .role(user.getRole())
                .createdDate(user.getCreatedDate())
                .build();
    }
}

