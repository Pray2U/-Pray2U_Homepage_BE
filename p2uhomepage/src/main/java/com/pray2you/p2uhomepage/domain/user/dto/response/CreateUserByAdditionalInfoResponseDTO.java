package com.pray2you.p2uhomepage.domain.user.dto.response;

import com.pray2you.p2uhomepage.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
public class CreateUserByAdditionalInfoResponseDTO {
    private final long userId;
    private final String githubId;
    private final String username;
    private final String phoneNumber;
    private final String email;
    private final String profileImgUrl;
    private final LocalDateTime createdDate;

    @Builder
    private CreateUserByAdditionalInfoResponseDTO(
            @NonNull Long userId,
            @NonNull String githubId,
            @NonNull String username,
            @NonNull String phoneNumber,
            @NonNull String email,
            @NonNull String profileImgUrl,
            @NonNull LocalDateTime createdDate) {
        this.userId = userId;
        this.githubId = githubId;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.profileImgUrl = profileImgUrl;
        this.createdDate = createdDate;
    }

    public static CreateUserByAdditionalInfoResponseDTO toDTO(User user){
        return CreateUserByAdditionalInfoResponseDTO.builder()
                .userId(user.getId())
                .githubId(user.getGithubId())
                .username(user.getUsername())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .profileImgUrl(user.getProfileImgUrl())
                .createdDate(user.getCreatedDate())
                .build();
    }
}
