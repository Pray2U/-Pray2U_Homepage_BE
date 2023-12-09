package com.pray2you.p2uhomepage.domain.user.dto.response;

import com.pray2you.p2uhomepage.domain.user.enumeration.Role;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
public class DeleteUserResponseDTO {

    private final long userId;
    private final String githubId;
    private final String username;
    private final String profileImgUrl;
    private final String phoneNumber;
    private final String email;
    private final Role role;
    private final LocalDateTime createdDate;
    private final LocalDateTime deletedDate;

    @Builder
    private DeleteUserResponseDTO(
            @NonNull Long userId,
            @NonNull String githubId,
            @NonNull String username,
            @NonNull String profileImgUrl,
            @NonNull String phoneNumber,
            @NonNull String email,
            @NonNull Role role,
            @NonNull LocalDateTime createdDate,
            @NonNull LocalDateTime deletedDate) {
        this.userId = userId;
        this.githubId = githubId;
        this.username = username;
        this.profileImgUrl = profileImgUrl;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
        this.createdDate = createdDate;
        this.deletedDate = deletedDate;
    }

    public static DeleteUserResponseDTO toDTO(User user){
        return builder()
                .userId(user.getId())
                .githubId(user.getGithubId())
                .username(user.getUsername())
                .profileImgUrl(user.getProfileImgUrl())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .role(user.getRole())
                .createdDate(user.getCreatedDate())
                .deletedDate(user.getModifiedDate())
                .build();
    }
}
