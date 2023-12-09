package com.pray2you.p2uhomepage.domain.memberapproval.dto.response;

import com.pray2you.p2uhomepage.domain.memberapproval.entity.MemberApproval;
import com.pray2you.p2uhomepage.domain.memberapproval.enumeration.ApprovalStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class ReadMemberApprovalResponseDTO {
    private final long memberApprovalId;
    private final String githubId;
    private final String username;
    private final ApprovalStatus status;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    @Builder
    private ReadMemberApprovalResponseDTO(
            @NonNull Long memberApprovalId,
            @NonNull String githubId,
            @NonNull String username,
            @NonNull ApprovalStatus status,
            @NonNull LocalDateTime createdDate,
            @NonNull LocalDateTime modifiedDate) {
        this.memberApprovalId = memberApprovalId;
        this.githubId = githubId;
        this.username = username;
        this.status = status;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static ReadMemberApprovalResponseDTO toDTO(MemberApproval memberApproval){
        return builder()
                .memberApprovalId(memberApproval.getId())
                .githubId(memberApproval.getGithubId())
                .username(memberApproval.getUsername())
                .status(memberApproval.getStatus())
                .createdDate(memberApproval.getCreatedDate())
                .modifiedDate(memberApproval.getModifiedDate())
                .build();
    }
}
