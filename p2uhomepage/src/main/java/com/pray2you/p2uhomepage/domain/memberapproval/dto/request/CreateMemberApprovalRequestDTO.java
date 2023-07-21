package com.pray2you.p2uhomepage.domain.memberapproval.dto.request;

import com.pray2you.p2uhomepage.domain.memberapproval.entity.MemberApproval;
import com.pray2you.p2uhomepage.domain.model.ApprovalStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateMemberApprovalRequestDTO {
    @NotBlank()
    private String githubId;
    @NotBlank
    private String username;

    public MemberApproval toEntity(){
        return new MemberApproval(githubId, username, ApprovalStatus.APPROVED);
    }
}
