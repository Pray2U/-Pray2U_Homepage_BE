package com.pray2you.p2uhomepage.domain.memberapproval.controller;

import com.pray2you.p2uhomepage.domain.memberapproval.dto.response.CreateMemberApprovalResponseDTO;
import com.pray2you.p2uhomepage.domain.memberapproval.dto.response.DeleteMemberApprovalResponseDTO;
import com.pray2you.p2uhomepage.domain.memberapproval.dto.response.ReadMemberApprovalResponseDTO;
import com.pray2you.p2uhomepage.domain.memberapproval.service.MemberApprovalService;
import com.pray2you.p2uhomepage.domain.memberapproval.enumeration.ApprovalStatus;
import com.pray2you.p2uhomepage.global.config.WebSecurityConfigure;
import com.pray2you.p2uhomepage.global.security.jwt.JwtAuthenticationFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers = MemberApprovalController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {WebSecurityConfigure.class, JwtAuthenticationFilter.class})
        })
@AutoConfigureMockMvc(addFilters = false)
class MemberApprovalControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private MemberApprovalService memberApprovalService;
//
//    @Test
//    @DisplayName("## 멤버 승인 등록 컨트롤러 테스트 ##")
//    void createMemberApprovals() throws Exception {
//        StringBuilder memberApprovalCreateRequestJson = new StringBuilder();
//        memberApprovalCreateRequestJson.append("{")
//                .append("\"githubId\": \"gildong Hong\",")
//                .append("\"username\": \"honggildong\"")
//                .append("}");
//
//        CreateMemberApprovalResponseDTO createMemberApprovalResponseDTO = CreateMemberApprovalResponseDTO.builder()
//                .memberApprovalId(1L)
//                .githubId("gildong Hong")
//                .username("honggildong")
//                .status(ApprovalStatus.APPROVED)
//                .createdDate(LocalDateTime.now())
//                .modifiedDate(LocalDateTime.now())
//                .build();
//
//        //given
//        BDDMockito.given(memberApprovalService.createMemberApprovals(any())).willReturn(createMemberApprovalResponseDTO);
//
//        String json = memberApprovalCreateRequestJson.toString();
//        //when
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/member-approvals")
//                .with(SecurityMockMvcRequestPostProcessors.csrf())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json));
//
//        //then
//        result.andExpect(MockMvcResultMatchers.status().isOk());
//
//    }
//
//
//
//    @Test
//    @DisplayName("## 멤버 승인 삭제 컨트롤러 테스트 ##")
//    void deleteMemberApprovals() throws Exception {
//
//        DeleteMemberApprovalResponseDTO deleteMemberApprovalResponseDTO = DeleteMemberApprovalResponseDTO.builder()
//                .githubId("gildong Hong")
//                .username("honggildong")
//                .status(ApprovalStatus.APPROVED)
//                .createDate(LocalDateTime.now())
//                .deletedDate(LocalDateTime.now())
//                .build();
//
//        //given
//        BDDMockito.given(memberApprovalService.deleteMemberApprovals(any())).willReturn(deleteMemberApprovalResponseDTO);
//
//        //when
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/admin/member-approvals/gildong Hong")
//                .with(SecurityMockMvcRequestPostProcessors.csrf()));
//
//        //then
//        result.andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    @DisplayName("## 멤버 승인 전체 조회 컨트롤러 테스트 ##")
//    void getAllMemberApprovals() throws Exception {
//
//        ReadMemberApprovalResponseDTO responseDTO = ReadMemberApprovalResponseDTO.builder()
//                .memberApprovalId(1L)
//                .githubId("gildong Hong")
//                .username("honggildong")
//                .status(ApprovalStatus.APPROVED)
//                .createdDate(LocalDateTime.now())
//                .modifiedDate(LocalDateTime.now())
//                .build();
//
//        List<ReadMemberApprovalResponseDTO> responseDTOs = new ArrayList<>();
//        responseDTOs.add(responseDTO);
//
//        Page<ReadMemberApprovalResponseDTO> responseDTOPage = new PageImpl<>(responseDTOs);
//
//        //given
//        BDDMockito.given(memberApprovalService.getAllMemberApprovals(any())).willReturn(responseDTOPage);
//
//        //when
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/member-approvals?page=0&size=1&sort=id,desc")
//                .with(SecurityMockMvcRequestPostProcessors.csrf()));
//
//        //then
//        result.andExpect(MockMvcResultMatchers.status().isOk());
//
//    }
}