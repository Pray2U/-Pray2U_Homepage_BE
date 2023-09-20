package com.pray2you.p2uhomepage.domain.til.service;

import com.google.gson.Gson;
import com.pray2you.p2uhomepage.domain.model.Role;
import com.pray2you.p2uhomepage.domain.post.dto.request.CreatePostRequestDTO;
import com.pray2you.p2uhomepage.domain.post.dto.response.CreatePostResponseDTO;
import com.pray2you.p2uhomepage.domain.til.dto.request.CreateTilRequestDTO;
import com.pray2you.p2uhomepage.domain.til.dto.request.UpdateTilRequestDTO;
import com.pray2you.p2uhomepage.domain.til.dto.response.CreateTilResponseDTO;
import com.pray2you.p2uhomepage.domain.til.dto.response.DeleteTilResponseDTO;
import com.pray2you.p2uhomepage.domain.til.dto.response.ReadTilResponseDTO;
import com.pray2you.p2uhomepage.domain.til.dto.response.UpdateTilResponseDTO;
import com.pray2you.p2uhomepage.domain.til.entity.Til;
import com.pray2you.p2uhomepage.domain.til.repository.TilRepository;
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
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class TilServiceTest {

    @Mock
    private TilRepository tilRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TilService tilService;

    @Test
    @DisplayName("## Til 등록 서비스 테스트 ##")
    void createTil() {

        StringBuilder tilCreateRequestJson = new StringBuilder();
        tilCreateRequestJson.append("{")
                .append("\"title\": \"Til입니다.\",")
                .append("\"content\": \"알고리즘\",")
                .append("\"tag\": \"알고리즘,dp\"")
                .append("}");

        CreateTilRequestDTO requestDTO = new Gson()
                .fromJson(tilCreateRequestJson.toString(), CreateTilRequestDTO.class);

        User user = User.builder()
                .username("honggildong")
                .email("gildong@gmail.com")
                .phoneNumber("010-0000-0000")
                .profileImgUrl("gildong.com/123rr")
                .githubId("gildong Hong")
                .role(Role.ROLE_USER)
                .build();

        Til til = Til.builder()
                .user(user)
                .title("Til입니다.")
                .content("알고리즘")
                .tag("알고리즘,dp")
                .build();

        CreateTilResponseDTO responseDTO = CreateTilResponseDTO.toDTO(til);

        //given
        BDDMockito.given(userRepository.findByIdAndDeleted(anyLong(), anyBoolean())).willReturn(Optional.of(user));
        BDDMockito.given(tilRepository.save(any())).willReturn(til);

        //when
        CreateTilResponseDTO result = tilService.createTil(user.getId(), requestDTO);

        //then
        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTO);

    }

    @Test
    @DisplayName("## Til 수정 서비스 테스트 ##")
    void updateTil() {

        StringBuilder tilUpdateRequestJson = new StringBuilder();
        tilUpdateRequestJson.append("{")
                .append("\"title\": \"Til입니다.\",")
                .append("\"content\": \"알고리즘\",")
                .append("\"tag\": \"알고리즘,dp\"")
                .append("}");

        UpdateTilRequestDTO requestDTO = new Gson()
                .fromJson(tilUpdateRequestJson.toString(), UpdateTilRequestDTO.class);

        User user = User.builder()
                .username("honggildong")
                .email("gildong@gmail.com")
                .phoneNumber("010-0000-0000")
                .profileImgUrl("gildong.com/123rr")
                .githubId("gildong Hong")
                .role(Role.ROLE_USER)
                .build();

        Til til = Til.builder()
                .user(user)
                .title("Til입니다.")
                .content("알고리즘")
                .tag("알고리즘,dp")
                .build();

        UpdateTilResponseDTO responseDTO = UpdateTilResponseDTO.toDTO(til);

        //given
        BDDMockito.given(userRepository.findByIdAndDeleted(anyLong(), anyBoolean())).willReturn(Optional.of(user));
        BDDMockito.given(tilRepository.findByIdAndDeleted(anyLong(), anyBoolean())).willReturn(Optional.of(til));
        BDDMockito.given(tilRepository.save(any())).willReturn(til);

        //when
        UpdateTilResponseDTO result = tilService.updateTil(user.getId(), til.getId(), requestDTO);

        //then
        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTO);
    }

    @Test
    @DisplayName("## Til 삭제 서비스 테스트 ##")
    void deleteTil() {

        User user = User.builder()
                .username("honggildong")
                .email("gildong@gmail.com")
                .phoneNumber("010-0000-0000")
                .profileImgUrl("gildong.com/123rr")
                .githubId("gildong Hong")
                .role(Role.ROLE_USER)
                .build();

        Til til = Til.builder()
                .user(user)
                .title("Til입니다.")
                .content("알고리즘")
                .tag("알고리즘,dp")
                .build();

        til.delete();

        DeleteTilResponseDTO responseDTO = DeleteTilResponseDTO.toDTO(til);

        //given
        BDDMockito.given(userRepository.findByIdAndDeleted(anyLong(), anyBoolean())).willReturn(Optional.of(user));
        BDDMockito.given(tilRepository.findByIdAndDeleted(anyLong(), anyBoolean())).willReturn(Optional.of(til));
        BDDMockito.given(tilRepository.save(any())).willReturn(til);

        //when
        DeleteTilResponseDTO result = tilService.deleteTil(user.getId(), til.getId());

        //then
        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTO);
    }

    @Test
    @DisplayName("## Til 전체 조회 서비스 테스트 ##")
    void readAllTil() {

        User user = User.builder()
                .username("honggildong")
                .email("gildong@gmail.com")
                .phoneNumber("010-0000-0000")
                .profileImgUrl("gildong.com/123rr")
                .githubId("gildong Hong")
                .role(Role.ROLE_USER)
                .build();

        Til til = Til.builder()
                .user(user)
                .title("Til입니다.")
                .content("알고리즘")
                .tag("알고리즘,dp")
                .build();

        ReadTilResponseDTO responseDTO = ReadTilResponseDTO.toDTO(til);

        Page<ReadTilResponseDTO> responseDTOPage = new PageImpl<>(List.of(responseDTO));

        //given
        BDDMockito.given(tilRepository.findAllByDeleted(any(), anyBoolean())).willReturn(new PageImpl<>(List.of(til)));

        //when
        Page<ReadTilResponseDTO> result = tilService.readAllTil(PageRequest.of(0, 20));

        //then
        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTOPage);
    }

    @Test
    @DisplayName("## Til 개별 조회 서비스 테스트 ##")
    void readTil() {
        User user = User.builder()
                .username("honggildong")
                .email("gildong@gmail.com")
                .phoneNumber("010-0000-0000")
                .profileImgUrl("gildong.com/123rr")
                .githubId("gildong Hong")
                .role(Role.ROLE_USER)
                .build();

        Til til = Til.builder()
                .user(user)
                .title("Til입니다.")
                .content("알고리즘")
                .tag("알고리즘,dp")
                .build();

        ReadTilResponseDTO responseDTO = ReadTilResponseDTO.toDTO(til);


        //given
        BDDMockito.given(tilRepository.findByIdAndDeleted(anyLong(), anyBoolean())).willReturn(Optional.of(til));

        //when
        ReadTilResponseDTO result = tilService.readTil(til.getId());

        //then
        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTO);
    }

    @Test
    @DisplayName("## Til 유저 조회 서비스 테스트 ##")
    void readUserTil() {
        User user = User.builder()
                .username("honggildong")
                .email("gildong@gmail.com")
                .phoneNumber("010-0000-0000")
                .profileImgUrl("gildong.com/123rr")
                .githubId("gildong Hong")
                .role(Role.ROLE_USER)
                .build();

        Til til = Til.builder()
                .user(user)
                .title("Til입니다.")
                .content("알고리즘")
                .tag("알고리즘,dp")
                .build();

        ReadTilResponseDTO responseDTO = ReadTilResponseDTO.toDTO(til);

        Page<ReadTilResponseDTO> responseDTOPage = new PageImpl<>(List.of(responseDTO));

        //given
        BDDMockito.given(userRepository.findByIdAndDeleted(anyLong(), anyBoolean())).willReturn(Optional.of(user));
        BDDMockito.given(tilRepository.findAllByUserAndDeleted(any(), any(), anyBoolean())).willReturn(new PageImpl<>(List.of(til)));

        //when
        Page<ReadTilResponseDTO> result = tilService.readUserTil(PageRequest.of(0, 20), 1L);

        //then
        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTOPage);
    }
}