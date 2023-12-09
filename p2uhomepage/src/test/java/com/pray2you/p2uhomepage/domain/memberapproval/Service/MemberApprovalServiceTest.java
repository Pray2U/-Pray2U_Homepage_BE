package com.pray2you.p2uhomepage.domain.memberapproval.Service;

import com.google.gson.Gson;
import com.pray2you.p2uhomepage.domain.memberapproval.dto.request.CreateMemberApprovalRequestDTO;
import com.pray2you.p2uhomepage.domain.memberapproval.dto.response.CreateMemberApprovalResponseDTO;
import com.pray2you.p2uhomepage.domain.memberapproval.dto.response.DeleteMemberApprovalResponseDTO;
import com.pray2you.p2uhomepage.domain.memberapproval.dto.response.ReadMemberApprovalResponseDTO;
import com.pray2you.p2uhomepage.domain.memberapproval.entity.MemberApproval;
import com.pray2you.p2uhomepage.domain.memberapproval.repository.MemberApprovalRepository;
import com.pray2you.p2uhomepage.domain.memberapproval.service.MemberApprovalService;
import com.pray2you.p2uhomepage.domain.memberapproval.enumeration.ApprovalStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class MemberApprovalServiceTest {

//    @Mock
//    private MemberApprovalRepository memberApprovalRepository;
//
//    @InjectMocks
//    private MemberApprovalService memberApprovalService;
//
//    @Test
//    @DisplayName("## 멤버 승인 등록 서비스 테스트 ##")
//    public void createMemberApprovals() {
//
//        StringBuilder memberApprovalCreateRequestJson = new StringBuilder();
//        memberApprovalCreateRequestJson.append("{")
//                .append("\"githubId\": \"gildong Hong\",")
//                .append("\"username\": \"honggildong\"")
//                .append("}");
//
//        CreateMemberApprovalRequestDTO createMemberApprovalRequestDTO = new Gson().fromJson(memberApprovalCreateRequestJson.toString(),CreateMemberApprovalRequestDTO.class);
//
//        MemberApproval memberApproval = new MemberApproval("gildong Hong", "honggildong", ApprovalStatus.APPROVED);
//
//        CreateMemberApprovalResponseDTO responseDTO = CreateMemberApprovalResponseDTO.toDTO(memberApproval);
//
//        //given
//        BDDMockito.given(memberApprovalRepository.findByGithubIdAndStatusNot(any(), any())).willReturn(Optional.empty());
//        BDDMockito.given(memberApprovalRepository.save(any())).willReturn(memberApproval);
//
//        //when
//        CreateMemberApprovalResponseDTO result = memberApprovalService.createMemberApprovals(createMemberApprovalRequestDTO);
//
//        //then
//        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTO);
//    }
//
//    @Test
//    @DisplayName("## 멤버 승인 삭제 서비스 테스트 ##")
//    public void deleteMemberApprovals() {
//
//        String githubId = "gildong Hong";
//
//        MemberApproval memberApproval = new MemberApproval("gildong Hong", "honggildong", ApprovalStatus.APPROVED);
//
//        MemberApproval deletedMemberApproval = new MemberApproval("gildong Hong", "honggildong", ApprovalStatus.DELETED);
//        DeleteMemberApprovalResponseDTO responseDTO = DeleteMemberApprovalResponseDTO.toDTO(deletedMemberApproval);
//
//        //given
//        BDDMockito.given(memberApprovalRepository.findByGithubIdAndStatus(any(), any())).willReturn(Optional.of(memberApproval));
//
//        BDDMockito.given(memberApprovalRepository.save(any())).willReturn(deletedMemberApproval);
//
//        //when
//        DeleteMemberApprovalResponseDTO result = memberApprovalService.deleteMemberApprovals(githubId);
//
//        //then
//        //멤버 상태 deleted로 바꾼후 dto로 변경 후 결과 비교
//        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTO);
//    }
//
//    @Test
//    @DisplayName("## 멤버 승인 조회 서비스 테스트 ##")
//    public void getAllMemberApprovals() {
//
//        Pageable pageable = PageRequest.of(0, 5);
//
//        MemberApproval memberApproval = new MemberApproval("gildong Hong", "honggildong", ApprovalStatus.APPROVED);
//
//        List<MemberApproval> memberApprovalList = new ArrayList<>();
//        memberApprovalList.add(memberApproval);
//
//        Page<MemberApproval> memberApprovalPage = new PageImpl<>(memberApprovalList);
//
//        Page<ReadMemberApprovalResponseDTO> responseDTO = memberApprovalPage
//                .map(ReadMemberApprovalResponseDTO::toDTO);
//
//        //given
//        BDDMockito.given(memberApprovalRepository.findByStatusNot(any(), any())).willReturn(memberApprovalPage);
//
//
//        //when
//        Page<ReadMemberApprovalResponseDTO> result = memberApprovalService.getAllMemberApprovals(pageable);
//
//        //then
//        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTO);
//    }

}