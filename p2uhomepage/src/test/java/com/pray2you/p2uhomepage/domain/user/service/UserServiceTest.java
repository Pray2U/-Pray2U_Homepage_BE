package com.pray2you.p2uhomepage.domain.user.service;

import com.google.gson.Gson;
import com.pray2you.p2uhomepage.domain.memberapproval.service.MemberApprovalService;
import com.pray2you.p2uhomepage.domain.user.enumeration.Role;
import com.pray2you.p2uhomepage.domain.point.entity.TotalPoint;
import com.pray2you.p2uhomepage.domain.point.repository.TotalPointRepository;
import com.pray2you.p2uhomepage.domain.user.dto.request.CreateUserByAdditionalInfoRequestDTO;
import com.pray2you.p2uhomepage.domain.user.dto.request.UpdateUserRequestDTO;
import com.pray2you.p2uhomepage.domain.user.dto.request.UpdateUserRoleRequestDTO;
import com.pray2you.p2uhomepage.domain.user.dto.response.*;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import com.pray2you.p2uhomepage.domain.user.repository.UserRepository;
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

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private MemberApprovalService memberApprovalService;
//
//    @Mock
//    private TotalPointRepository totalPointRepository;
//
//    @InjectMocks
//    private UserService userService;
//
//    @Test
//    @DisplayName("## 멤버 추가 승인 등록 서비스 테스트 ##")
//    void createUserByAdditionalInfo() {
//        StringBuilder userCreateRequestJson = new StringBuilder();
//        userCreateRequestJson.append("{")
//                .append("\"username\": \"honggildong\",")
//                .append("\"phoneNumber\": \"010-0000-0000\",")
//                .append("\"email\": \"gildong@gmail.com\",")
//                .append("\"profileImgUrl\": \"gildong.com/123rr\"")
//                .append("}");
//
//        CreateUserByAdditionalInfoRequestDTO requestDTO
//                = new Gson()
//                    .fromJson(userCreateRequestJson.toString(), CreateUserByAdditionalInfoRequestDTO.class);
//
//        User user = User.builder()
//                .username("honggildong")
//                .email("gildong@gmail.com")
//                .phoneNumber("010-0000-0000")
//                .profileImgUrl("gildong.com/123rr")
//                .githubId("gildong Hong")
//                .role(Role.ROLE_USER)
//                .build();
//
//        TotalPoint totalPoint = new TotalPoint(user);
//
//        CreateUserByAdditionalInfoResponseDTO responseDTO = CreateUserByAdditionalInfoResponseDTO.toDTO(user);
//
//        //given
//        BDDMockito.given(userRepository.findByIdAndDeleted(any(), anyBoolean())).willReturn(Optional.of(user));
//        BDDMockito.given(totalPointRepository.save(any())).willReturn(totalPoint);
//        BDDMockito.given(userRepository.save(any())).willReturn(user);
//
//        //when
//        CreateUserByAdditionalInfoResponseDTO result = userService.createUserByAdditionalInfo(user.getId(), requestDTO);
//
//        //then
//        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTO);
//    }
//
//    @Test
//    @DisplayName("## 멤버 삭제 서비스 테스트 ##")
//    void deleteUser() {
//
//        User user = User.builder()
//                .username("honggildong")
//                .email("gildong@gmail.com")
//                .phoneNumber("010-0000-0000")
//                .profileImgUrl("gildong.com/123rr")
//                .githubId("gildong Hong")
//                .role(Role.ROLE_USER)
//                .build();
//
//        DeleteUserResponseDTO responseDTO = DeleteUserResponseDTO.toDTO(user);
//
//        //given
//        BDDMockito.given(userRepository.findByIdAndDeleted(any(), anyBoolean())).willReturn(Optional.of(user));
//        user.delete();
//        BDDMockito.given(userRepository.save(any())).willReturn(user);
//
//        //when
//        DeleteUserResponseDTO result = userService.deleteUser(user.getId());
//
//        //then
//        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTO);
//    }
//    @Test
//    @DisplayName("## 멤버 수정 서비스 테스트 ##")
//    void updateUser() {
//        StringBuilder userUpdateRequestJson = new StringBuilder();
//        userUpdateRequestJson.append("{")
//                .append("\"phoneNumber\": \"010-0000-0000\",")
//                .append("\"profileImgUrl\": \"gildong.com/123rr\"")
//                .append("}");
//
//        UpdateUserRequestDTO requestDTO
//                = new Gson()
//                .fromJson(userUpdateRequestJson.toString(), UpdateUserRequestDTO.class);
//
//        User user = User.builder()
//                .username("honggildong")
//                .email("gildong@gmail.com")
//                .phoneNumber("010-0000-0000")
//                .profileImgUrl("gildong.com/123rr")
//                .githubId("gildong Hong")
//                .role(Role.ROLE_USER)
//                .build();
//
//        UpdateUserResponseDTO responseDTO = UpdateUserResponseDTO.toDTO(user);
//
//        //given
//        BDDMockito.given(userRepository.findByIdAndDeleted(any(), anyBoolean())).willReturn(Optional.of(user));
//        BDDMockito.given(userRepository.save(any())).willReturn(user);
//
//        //when
//        UpdateUserResponseDTO result = userService.updateUser(user.getId(), requestDTO);
//
//        //then
//        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTO);
//    }
//
//    @Test
//    @DisplayName("## 본인 정보 조회 서비스 테스트 ##")
//    void readUserInfo() {
//
//        User user = User.builder()
//                .username("honggildong")
//                .email("gildong@gmail.com")
//                .phoneNumber("010-0000-0000")
//                .profileImgUrl("gildong.com/123rr")
//                .githubId("gildong Hong")
//                .role(Role.ROLE_USER)
//                .build();
//
//        ReadUserInfoResponseDTO responseDTO = ReadUserInfoResponseDTO.toDTO(user);
//
//        //given
//        BDDMockito.given(userRepository.findByIdAndDeleted(any(), anyBoolean())).willReturn(Optional.of(user));
//
//        //when
//        ReadUserInfoResponseDTO result = userService.readUserInfo(user.getId());
//
//        //then
//        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTO);
//    }
//
//    @Test
//    @DisplayName("## 전체 유저 조회 서비스 테스트 ##")
//    void readAllUser() {
//
//        User user = User.builder()
//                .username("honggildong")
//                .email("gildong@gmail.com")
//                .phoneNumber("010-0000-0000")
//                .profileImgUrl("gildong.com/123rr")
//                .githubId("gildong Hong")
//                .role(Role.ROLE_USER)
//                .build();
//
//        ReadUserInfoResponseDTO responseDTO = ReadUserInfoResponseDTO.toDTO(user);
//        Page<User> allUserPage = new PageImpl<>(List.of(user));
//        Page<ReadUserInfoResponseDTO> responseDTOPage = new PageImpl<>(List.of(responseDTO));
//
//        //given
//        BDDMockito.given(userRepository.findByDeleted(any(), anyBoolean())).willReturn(allUserPage);
//
//        //when
//        Page<ReadUserInfoResponseDTO> result = userService.readAllUser(PageRequest.of(0, 20));
//
//        //then
//        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTOPage);
//    }
//
//    @Test
//    @DisplayName("## 멤버 권한 수정 서비스 테스트 ##")
//    void updateRole() {
//        StringBuilder updateRoleRequestJson = new StringBuilder();
//        updateRoleRequestJson.append("{")
//                .append("\"role\": \"ROLE_ADMIN\"")
//                .append("}");
//
//        UpdateUserRoleRequestDTO requestDTO
//                = new Gson()
//                .fromJson(updateRoleRequestJson.toString(), UpdateUserRoleRequestDTO.class);
//
//        User user = User.builder()
//                .username("honggildong")
//                .email("gildong@gmail.com")
//                .phoneNumber("010-0000-0000")
//                .profileImgUrl("gildong.com/123rr")
//                .githubId("gildong Hong")
//                .role(Role.ROLE_USER)
//                .build();
//
//
//        //given
//        BDDMockito.given(userRepository.findByGithubIdAndDeleted(any(), anyBoolean())).willReturn(Optional.of(user));
//        user.updateRole(Role.ROLE_ADMIN);
//        UpdateRoleResponseDTO responseDTO = UpdateRoleResponseDTO.toDTO(user);
//        BDDMockito.given(userRepository.save(any())).willReturn(user);
//
//        //when
//        UpdateRoleResponseDTO result = userService.updateRole(user.getId(), requestDTO);
//
//        //then
//        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTO);
//    }

}