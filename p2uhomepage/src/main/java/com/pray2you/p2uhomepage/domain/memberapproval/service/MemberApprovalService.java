package com.pray2you.p2uhomepage.domain.memberapproval.service;

import com.pray2you.p2uhomepage.domain.memberapproval.dto.request.CreateMemberApprovalRequestDTO;
import com.pray2you.p2uhomepage.domain.memberapproval.dto.response.CreateMemberApprovalResponseDTO;
import com.pray2you.p2uhomepage.domain.memberapproval.dto.response.DeleteMemberApprovalResponseDTO;
import com.pray2you.p2uhomepage.domain.memberapproval.dto.response.ReadMemberApprovalResponseDTO;
import com.pray2you.p2uhomepage.domain.memberapproval.entity.MemberApproval;
import com.pray2you.p2uhomepage.domain.memberapproval.repository.MemberApprovalRepository;
import com.pray2you.p2uhomepage.domain.model.ApprovalStatus;
import com.pray2you.p2uhomepage.global.exception.ErrorCode.UserErrorCode;
import com.pray2you.p2uhomepage.global.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberApprovalService {

    private final MemberApprovalRepository memberApprovalRepository;

    public CreateMemberApprovalResponseDTO createMemberApprovals(CreateMemberApprovalRequestDTO requestDTO){

        memberApprovalRepository.findByGithubIdAndStatusNot(requestDTO.getGithubId(), ApprovalStatus.DELETED)
                .ifPresent(value -> {
                    throw new RestApiException(UserErrorCode.DUPLICATE_APPROVAL_EXCEPTION); // 값이 있으면 에러를 발생시킴
                });

        MemberApproval savedMemberApproval = memberApprovalRepository.save(requestDTO.toEntity());

        return CreateMemberApprovalResponseDTO.toDTO(savedMemberApproval);
    }

    public DeleteMemberApprovalResponseDTO deleteMemberApprovals(String githubId){

        MemberApproval memberApproval = memberApprovalRepository.findByGithubIdAndStatus(githubId, ApprovalStatus.APPROVED)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_APPROVAL_EXCEPTION));

        //삭제 상태로 변경
        memberApproval.updateStatus(ApprovalStatus.DELETED);

        MemberApproval deletedMemberApproval = memberApprovalRepository.save(memberApproval);
        return DeleteMemberApprovalResponseDTO.toDTO(deletedMemberApproval);
    }

    public Page<ReadMemberApprovalResponseDTO> getAllMemberApprovals(Pageable pageable){
        Page<MemberApproval> memberApprovals = memberApprovalRepository.findByStatusNot(ApprovalStatus.DELETED, pageable);
        return memberApprovals
                .map(ReadMemberApprovalResponseDTO::toDTO);
    }

    public void updateApprovalStatus(String githubId, ApprovalStatus approvalStatus) {
        MemberApproval memberApproval = memberApprovalRepository.findByGithubIdAndStatusNot(githubId, ApprovalStatus.DELETED)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_APPROVAL_EXCEPTION));
        memberApproval.updateStatus(approvalStatus);
        memberApprovalRepository.save(memberApproval);
    }
}
