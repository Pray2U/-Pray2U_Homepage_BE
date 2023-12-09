package com.pray2you.p2uhomepage.domain.user.entity;

import com.pray2you.p2uhomepage.global.config.BaseTimeEntity;
import com.pray2you.p2uhomepage.domain.user.enumeration.Role;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(nullable = false)
    private String githubId;

    @Column(nullable = false)
    private String username;

    @Column
    private String profileImgUrl;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private boolean deleted = false;

    @Builder
    private User(
            @NonNull String githubId,
            @NonNull String username,
            @NonNull String profileImgUrl,
            @NonNull String phoneNumber,
            @NonNull String email,
            @NonNull Role role) {
        this.githubId = githubId;
        this.username = username;
        this.profileImgUrl = profileImgUrl;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
    }

    public User updateForOauth(User updateUser) {
        this.githubId = updateUser.githubId;
        this.username = updateUser.username;
        return this;
    }

    public void addInformation(String username, String phoneNumber, String email, String profileImgUrl) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.profileImgUrl = profileImgUrl == null ? this.profileImgUrl : profileImgUrl;
        this.role = Role.ROLE_USER;
    }

    public void delete(){
        this.deleted = true;
    }

    public void update(String phoneNumber, String profileImgUrl){
        this.phoneNumber = phoneNumber;
        this.profileImgUrl = profileImgUrl;
    }

    public void updateRole(Role role){
        this.role = role;
    }
}
