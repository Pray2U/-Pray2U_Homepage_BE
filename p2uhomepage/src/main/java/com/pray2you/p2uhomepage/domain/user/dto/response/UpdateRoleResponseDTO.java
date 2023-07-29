package com.pray2you.p2uhomepage.domain.user.dto.response;

import com.pray2you.p2uhomepage.domain.model.Role;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateRoleResponseDTO {

    private Long userId;
    private String githubId;
    private String username;
    private String phoneNumber;
    private String profileImgUrl;
    private String email;
    private Role role;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    private UpdateRoleResponseDTO(Long userId, String githubId, String username, String phoneNumber, String profileImgUrl, String email, Role role, LocalDateTime createdDate, LocalDateTime modifiedDate) {
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
