package com.pray2you.p2uhomepage.domain.memberapproval.dto.response;

import com.pray2you.p2uhomepage.domain.memberapproval.entity.MemberApproval;
import com.pray2you.p2uhomepage.domain.model.ApprovalStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DeleteMemberApprovalResponseDTO {
    public String githubId;
    public String username;
    public ApprovalStatus status;
    public LocalDateTime createDate;
    public LocalDateTime deletedDate;

    @Builder
    public DeleteMemberApprovalResponseDTO(String githubId, String username, ApprovalStatus status, LocalDateTime createDate, LocalDateTime deletedDate) {
        this.githubId = githubId;
        this.username = username;
        this.status = status;
        this.createDate = createDate;
        this.deletedDate = deletedDate;
    }

    public static DeleteMemberApprovalResponseDTO toDTO(MemberApproval memberApproval){
        return builder()
                .githubId(memberApproval.getGithubId())
                .username(memberApproval.getUsername())
                .status(memberApproval.getStatus())
                .createDate(memberApproval.getCreatedDate())
                .deletedDate(memberApproval.getModifiedDate())
                .build();
    }
}
