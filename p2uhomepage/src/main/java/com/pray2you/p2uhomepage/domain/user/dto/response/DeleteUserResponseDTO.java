package com.pray2you.p2uhomepage.domain.user.dto.response;

import com.pray2you.p2uhomepage.domain.model.Role;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DeleteUserResponseDTO {

    private Long userId;
    private String githubId;
    private String username;
    private String profileImgUrl;
    private String phoneNumber;
    private String email;
    private Role role;
    private LocalDateTime createdDate;
    private LocalDateTime deletedDate;

    @Builder
    private DeleteUserResponseDTO(Long userId, String githubId, String username, String profileImgUrl, String phoneNumber, String email, Role role, LocalDateTime createdDate, LocalDateTime deletedDate) {
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
