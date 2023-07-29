package com.pray2you.p2uhomepage.domain.user.dto.response;

import com.pray2you.p2uhomepage.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateUserByAdditionalInfoResponseDTO {
    private Long userId;
    private String githubId;
    private String username;
    private String phoneNumber;
    private String email;
    private String profileImgUrl;
    private LocalDateTime createdDate;

    @Builder
    private CreateUserByAdditionalInfoResponseDTO(Long userId, String githubId, String username, String phoneNumber, String email, String profileImgUrl, LocalDateTime createdDate) {
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
