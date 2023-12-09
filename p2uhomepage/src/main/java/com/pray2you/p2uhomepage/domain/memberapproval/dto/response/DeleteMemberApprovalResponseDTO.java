package com.pray2you.p2uhomepage.domain.memberapproval.dto.response;

import com.pray2you.p2uhomepage.domain.memberapproval.entity.MemberApproval;
import com.pray2you.p2uhomepage.domain.memberapproval.enumeration.ApprovalStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class DeleteMemberApprovalResponseDTO {
    private final long memberApprovalId;
    private final String githubId;
    private final String username;
    private final ApprovalStatus status;
    private final LocalDateTime createDate;
    private final LocalDateTime deletedDate;

    @Builder
    private DeleteMemberApprovalResponseDTO(
            @NonNull Long memberApprovalId,
            @NonNull String githubId,
            @NonNull String username,
            @NonNull ApprovalStatus status,
            @NonNull LocalDateTime createDate,
            @NonNull LocalDateTime deletedDate) {
        this.memberApprovalId = memberApprovalId;
        this.githubId = githubId;
        this.username = username;
        this.status = status;
        this.createDate = createDate;
        this.deletedDate = deletedDate;
    }

    public static DeleteMemberApprovalResponseDTO toDTO(MemberApproval memberApproval){
        return builder()
                .memberApprovalId(memberApproval.getId())
                .githubId(memberApproval.getGithubId())
                .username(memberApproval.getUsername())
                .status(memberApproval.getStatus())
                .createDate(memberApproval.getCreatedDate())
                .deletedDate(memberApproval.getModifiedDate())
                .build();
    }
}
