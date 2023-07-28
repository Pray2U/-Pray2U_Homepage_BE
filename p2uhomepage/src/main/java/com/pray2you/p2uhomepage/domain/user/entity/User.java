package com.pray2you.p2uhomepage.domain.user.entity;

import com.pray2you.p2uhomepage.domain.model.BaseTimeEntity;
import com.pray2you.p2uhomepage.domain.model.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    public User(String githubId, String username, String profileImgUrl, String phoneNumber, String email, Role role) {
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

    public User addInformation(String username, String phoneNumber, String email, String profileImgUrl) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.profileImgUrl = profileImgUrl;
        this.role = Role.ROLE_USER;
        return this;
    }

    public User delete(){
        this.deleted = true;
        return this;
    }

    public User update(String phoneNumber, String profileImgUrl){
        this.phoneNumber = phoneNumber;
        this.profileImgUrl = profileImgUrl;
        return this;
    }

    public User updateRole(Role role){
        this.role = role;
        return this;
    }
}
