package com.pray2you.p2uhomepage.domain.memberapproval.entity;

import com.pray2you.p2uhomepage.domain.model.ApprovalStatus;
import com.pray2you.p2uhomepage.domain.model.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberApproval extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberapproval_id")
    private long id;

    @Column(nullable = false)
    private String githubId;

    @Column(nullable = false)
    private String username;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private ApprovalStatus status;

    public MemberApproval(String githubId, String username, ApprovalStatus status) {
        this.githubId = githubId;
        this.username = username;
        this.status = status;
    }

    public void updateStatus(ApprovalStatus approvalStatus) {
        this.status = approvalStatus;
    }
}
