package com.pray2you.p2uhomepage.domain.memberapproval.dto.response;

import com.pray2you.p2uhomepage.domain.memberapproval.entity.MemberApproval;
import com.pray2you.p2uhomepage.domain.model.ApprovalStatus;
import lombok.Builder;
import lombok.Getter;

import java.lang.reflect.Member;
import java.time.LocalDateTime;

@Getter
public class ReadMemberApprovalResponseDTO {
    public Long memberApprovalId;
    public String githubId;
    public String username;
    public ApprovalStatus status;
    public LocalDateTime createdDate;
    public LocalDateTime modifiedDate;

    @Builder
    public ReadMemberApprovalResponseDTO(Long memberApprovalId, String githubId, String username, ApprovalStatus status, LocalDateTime createdDate, LocalDateTime modifiedDate) {
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
