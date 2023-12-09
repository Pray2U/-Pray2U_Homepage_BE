package com.pray2you.p2uhomepage.domain.user.dto.response;

import com.pray2you.p2uhomepage.domain.user.enumeration.Role;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
public class UpdateRoleResponseDTO {

    private final long userId;
    private final String githubId;
    private final String username;
    private final String phoneNumber;
    private final String profileImgUrl;
    private final String email;
    private final Role role;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    @Builder
    private UpdateRoleResponseDTO(
            @NonNull Long userId,
            @NonNull String githubId,
            @NonNull String username,
            @NonNull String phoneNumber,
            @NonNull String profileImgUrl,
            @NonNull String email,
            @NonNull Role role,
            @NonNull LocalDateTime createdDate,
            @NonNull LocalDateTime modifiedDate) {
        this.userId = userId;
        this.githubId = githubId;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.profileImgUrl = profileImgUrl;
        this.email = email;
        this.role = role;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static UpdateRoleResponseDTO toDTO(User user){
        return builder()
                .userId(user.getId())
                .githubId(user.getGithubId())
                .username(user.getUsername())
                .phoneNumber(user.getPhoneNumber())
                .profileImgUrl(user.getProfileImgUrl())
                .email(user.getEmail())
                .role(user.getRole())
                .createdDate(user.getCreatedDate())
                .modifiedDate(user.getModifiedDate())
                .build();
    }

}
