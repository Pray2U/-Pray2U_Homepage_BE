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

        MemberApproval memberApproval = memberApprovalRepository.findByGithubId(requestDTO.getGithubId())
                .orElse(requestDTO.toEntity());

        if (memberApproval.getStatus() == ApprovalStatus.JOINED) {
            throw new RestApiException(UserErrorCode.DUPLICATE_APPROVAL_EXCEPTION);
        }

        //이미 delete되었던 approval일 경우, 다시 생성
        memberApproval.create();
        MemberApproval savedMemberApproval = memberApprovalRepository.save(memberApproval);

        return CreateMemberApprovalResponseDTO.toDTO(savedMemberApproval);
    }

    public DeleteMemberApprovalResponseDTO deleteMemberApprovals(String githubId){

        MemberApproval memberApproval = memberApprovalRepository.findByGithubId(githubId)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_APPROVAL_EXCEPTION));

        if (memberApproval.getStatus() == ApprovalStatus.DELETED) {
            throw new RestApiException(UserErrorCode.NOT_EXIST_APPROVAL_EXCEPTION);
        }

        //삭제 상태로 변경
        memberApproval.delete();

        MemberApproval deletedMemberApproval = memberApprovalRepository.save(memberApproval);

        return DeleteMemberApprovalResponseDTO.toDTO(deletedMemberApproval);
    }

    public Page<ReadMemberApprovalResponseDTO> getAllMemberApprovals(Pageable pageable){
        Page<MemberApproval> memberApprovals = memberApprovalRepository.findAll(pageable);
        log.info("조회완료");
        return memberApprovals
                .map(ReadMemberApprovalResponseDTO::toDTO);
    }


}
