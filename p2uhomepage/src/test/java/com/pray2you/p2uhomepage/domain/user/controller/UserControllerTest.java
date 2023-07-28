package com.pray2you.p2uhomepage.domain.user.controller;

import com.pray2you.p2uhomepage.auth.WithMockCustomUser;
import com.pray2you.p2uhomepage.domain.user.dto.response.*;
import com.pray2you.p2uhomepage.domain.user.service.UserService;
import com.pray2you.p2uhomepage.global.config.WebSecurityConfigure;
import com.pray2you.p2uhomepage.global.security.jwt.JwtAuthenticationFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers = UserController.class,
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {WebSecurityConfigure.class, JwtAuthenticationFilter.class})
    })
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @WithMockCustomUser
    @DisplayName("## 멤버 추가 승인 등록 컨트롤러 테스트 ##")
    void createUserByAdditionalInfo() throws Exception {
        StringBuilder userCreateRequestJson = new StringBuilder();
        userCreateRequestJson.append("{")
                .append("\"username\": \"honggildong\",")
                .append("\"phoneNumber\": \"010-0000-0000\",")
                .append("\"email\": \"gildong@gmail.com\",")
                .append("\"profileImgUrl\": \"gildong.com/123rr\"")
                .append("}");

        CreateUserByAdditionalInfoResponseDTO responseDTO = CreateUserByAdditionalInfoResponseDTO.builder()
                .userId(1L)
                .githubId("gildong Hong")
                .username("honggildong")
                .profileImgUrl("gildong.com/123rr")
                .email("gildong@gmail.com")
                .phoneNumber("010-0000-0000")
                .createdDate(LocalDateTime.now())
                .build();

        //given
        BDDMockito.given(userService.createUserByAdditionalInfo(any(), any())).willReturn(responseDTO);

        String json = userCreateRequestJson.toString();
        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/users/infoform")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        //then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockCustomUser
    @DisplayName("## 멤버 삭제 컨트롤러 테스트 ##")
    void deleteUser() throws Exception{

        DeleteUserResponseDTO responseDTO = DeleteUserResponseDTO.builder()
                .userId(1L)
                .githubId("gildong Hong")
                .username("honggildong")
                .profileImgUrl("gildong.com/123rr")
                .email("gildong@gmail.com")
                .phoneNumber("010-0000-0000")
                .createdDate(LocalDateTime.now())
                .deletedDate(LocalDateTime.now())
                .build();

        //given
        BDDMockito.given(userService.deleteUser(any())).willReturn(responseDTO);

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/users")
                .with(SecurityMockMvcRequestPostProcessors.csrf()));

        //then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockCustomUser
    @DisplayName("## 멤버 수정 컨트롤러 테스트 ##")
    void updateUser() throws Exception {
        StringBuilder userUpdateRequestJson = new StringBuilder();
        userUpdateRequestJson.append("{")
                .append("\"phoneNumber\": \"010-0000-0000\",")
                .append("\"profileImgUrl\": \"gildong.com/123rr\"")
                .append("}");

        UpdateUserResponseDTO responseDTO = UpdateUserResponseDTO.builder()
                .userId(1L)
                .githubId("gildong Hong")
                .username("honggildong")
                .profileImgUrl("gildong.com/123rr")
                .email("gildong@gmail.com")
                .phoneNumber("010-0000-0000")
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        //given
        BDDMockito.given(userService.updateUser(any(), any())).willReturn(responseDTO);

        String json = userUpdateRequestJson.toString();
        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/api/users")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        //then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockCustomUser
    @DisplayName("## 본인 정보 조회 컨트롤러 테스트 ##")
    void readUserInfo() throws Exception {
        ReadUserInfoResponseDTO responseDTO = ReadUserInfoResponseDTO.builder()
                .userId(1L)
                .githubId("gildong Hong")
                .username("honggildong")
                .profileImgUrl("gildong.com/123rr")
                .email("gildong@gmail.com")
                .phoneNumber("010-0000-0000")
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        //given
        BDDMockito.given(userService.readUserInfo(any())).willReturn(responseDTO);

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/users/me")
                .with(SecurityMockMvcRequestPostProcessors.csrf()));

        //then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockCustomUser
    @DisplayName("## 전체 유저 조회 컨트롤러 테스트 ##")
    void readAllUser() throws Exception {

        ReadUserInfoResponseDTO responseDTO = ReadUserInfoResponseDTO.builder()
                .userId(1L)
                .githubId("gildong Hong")
                .username("honggildong")
                .profileImgUrl("gildong.com/123rr")
                .email("gildong@gmail.com")
                .phoneNumber("010-0000-0000")
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        Page<ReadUserInfoResponseDTO> responseDTOPage = new PageImpl<>(List.of(responseDTO));

        //given
        BDDMockito.given(userService.readAllUser(any())).willReturn(responseDTOPage);

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/users")
                .with(SecurityMockMvcRequestPostProcessors.csrf()));

        //then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockCustomUser
    @DisplayName("## 유저 권한 변경 컨트롤러 테스트 ##")
    void updateRole() throws Exception {

        StringBuilder updateRoleRequestJson = new StringBuilder();
        updateRoleRequestJson.append("{")
                .append("\"role\": \"ROLE_USER\"")
                .append("}");

        UpdateRoleResponseDTO responseDTO = UpdateRoleResponseDTO.builder()
                .userId(1L)
                .githubId("gildong Hong")
                .username("honggildong")
                .profileImgUrl("gildong.com/123rr")
                .email("gildong@gmail.com")
                .phoneNumber("010-0000-0000")
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        //given
        BDDMockito.given(userService.updateRole(any(),any())).willReturn(responseDTO);
        String json = updateRoleRequestJson.toString();

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/admin/users/role/gildong Hong")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        //then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }
}