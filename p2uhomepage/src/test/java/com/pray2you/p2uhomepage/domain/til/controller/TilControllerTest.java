//package com.pray2you.p2uhomepage.domain.til.controller;
//
//import com.pray2you.p2uhomepage.auth.WithMockCustomUser;
//import com.pray2you.p2uhomepage.domain.post.controller.PostController;
//import com.pray2you.p2uhomepage.domain.til.dto.request.CreateTilRequestDTO;
//import com.pray2you.p2uhomepage.domain.til.dto.response.CreateTilResponseDTO;
//import com.pray2you.p2uhomepage.domain.til.dto.response.DeleteTilResponseDTO;
//import com.pray2you.p2uhomepage.domain.til.dto.response.ReadTilResponseDTO;
//import com.pray2you.p2uhomepage.domain.til.dto.response.UpdateTilResponseDTO;
//import com.pray2you.p2uhomepage.domain.til.service.TilService;
//import com.pray2you.p2uhomepage.global.config.WebSecurityConfigure;
//import com.pray2you.p2uhomepage.global.security.jwt.JwtAuthenticationFilter;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.BDDMockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.FilterType;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//
//@WebMvcTest(controllers = TilController.class,
//        excludeFilters = {
//                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {WebSecurityConfigure.class, JwtAuthenticationFilter.class})
//        })
//class TilControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private TilService tilService;
//
//    @Test
//    @WithMockCustomUser
//    @DisplayName("## TIL 등록 컨트롤러 테스트 ##")
//    void createTil() throws Exception {
//
//        StringBuilder tilCreateRequestJson = new StringBuilder();
//        tilCreateRequestJson.append("{")
//                .append("\"title\": \"Til입니다.\",")
//                .append("\"content\": \"알고리즘\",")
//                .append("\"tag\": \"알고리즘,dp\"")
//                .append("}");
//
//        CreateTilResponseDTO responseDTO = CreateTilResponseDTO.builder()
//                .tilId(1L)
//                .title("Til입니다.")
//                .content("알고리즘")
//                .tag("알고리즘,dp")
//                .username("gildong Hong")
//                .userId(1L)
//                .userImg("")
//                .createdDate(LocalDateTime.now())
//                .build();
//
//        //given
//        BDDMockito.given(tilService.createTil(anyLong(), any())).willReturn(responseDTO);
//
//        String json = tilCreateRequestJson.toString();
//        //when
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/tils")
//                .with(SecurityMockMvcRequestPostProcessors.csrf())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json));
//
//        //then
//        result.andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    @WithMockCustomUser
//    @DisplayName("## TIL 수정 컨트롤러 테스트 ##")
//    void updateTil() throws Exception {
//        StringBuilder tilUpdateRequestJson = new StringBuilder();
//        tilUpdateRequestJson.append("{")
//                .append("\"title\": \"Til입니다.\",")
//                .append("\"content\": \"알고리즘\",")
//                .append("\"tag\": \"알고리즘,dp\"")
//                .append("}");
//
//        UpdateTilResponseDTO responseDTO = UpdateTilResponseDTO.builder()
//                .tilId(1L)
//                .title("Til입니다.")
//                .content("알고리즘")
//                .tag("알고리즘,dp")
//                .username("gildong Hong")
//                .userId(1L)
//                .userImg("")
//                .createdDate(LocalDateTime.now())
//                .modifiedDate(LocalDateTime.now())
//                .build();
//
//        //given
//        BDDMockito.given(tilService.updateTil(anyLong(), anyLong(), any())).willReturn(responseDTO);
//
//        String json = tilUpdateRequestJson.toString();
//        //when
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/api/tils/1")
//                .with(SecurityMockMvcRequestPostProcessors.csrf())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json));
//
//        //then
//        result.andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    @WithMockCustomUser
//    @DisplayName("## TIL 삭제 컨트롤러 테스트 ##")
//    void deleteTil() throws Exception {
//        DeleteTilResponseDTO responseDTO = DeleteTilResponseDTO.builder()
//                .tilId(1L)
//                .title("Til입니다.")
//                .content("알고리즘")
//                .tag("알고리즘,dp")
//                .username("gildong Hong")
//                .userId(1L)
//                .userImg("")
//                .createdDate(LocalDateTime.now())
//                .deletedDate(LocalDateTime.now())
//                .build();
//
//        //given
//        BDDMockito.given(tilService.deleteTil(anyLong(), anyLong())).willReturn(responseDTO);
//
//        //when
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/tils/1")
//                .with(SecurityMockMvcRequestPostProcessors.csrf()));
//        //then
//        result.andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    @WithMockCustomUser
//    @DisplayName("## TIL 전체 조회 컨트롤러 테스트 ##")
//    void readAllTil() throws Exception {
//
//        ReadTilResponseDTO responseDTO = ReadTilResponseDTO.builder()
//                .tilId(1L)
//                .title("Til입니다.")
//                .content("알고리즘")
//                .tag("알고리즘,dp")
//                .username("gildong Hong")
//                .userId(1L)
//                .userImg("")
//                .createdDate(LocalDateTime.now())
//                .modifiedDate(LocalDateTime.now())
//                .build();
//
//        Page<ReadTilResponseDTO> responseDTOPage = new PageImpl<>(List.of(responseDTO));
//
//        //given
//        BDDMockito.given(tilService.readAllTil(any())).willReturn(responseDTOPage);
//
//        //when
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/tils")
//                .with(SecurityMockMvcRequestPostProcessors.csrf()));
//        //then
//        result.andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    @WithMockCustomUser
//    @DisplayName("## TIL 개별 조회 컨트롤러 테스트 ##")
//    void readTil() throws Exception {
//
//        ReadTilResponseDTO responseDTO = ReadTilResponseDTO.builder()
//                .tilId(1L)
//                .title("Til입니다.")
//                .content("알고리즘")
//                .tag("알고리즘,dp")
//                .username("gildong Hong")
//                .userId(1L)
//                .userImg("")
//                .createdDate(LocalDateTime.now())
//                .modifiedDate(LocalDateTime.now())
//                .build();
//
//        //given
//        BDDMockito.given(tilService.readTil(anyLong())).willReturn(responseDTO);
//
//        //when
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/tils/1")
//                .with(SecurityMockMvcRequestPostProcessors.csrf()));
//        //then
//        result.andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    @WithMockCustomUser
//    @DisplayName("## TIL 유저 조회 컨트롤러 테스트 ##")
//    void readUserTil() throws Exception {
//        ReadTilResponseDTO responseDTO = ReadTilResponseDTO.builder()
//                .tilId(1L)
//                .title("Til입니다.")
//                .content("알고리즘")
//                .tag("알고리즘,dp")
//                .username("gildong Hong")
//                .userId(1L)
//                .userImg("")
//                .createdDate(LocalDateTime.now())
//                .modifiedDate(LocalDateTime.now())
//                .build();
//
//        Page<ReadTilResponseDTO> responseDTOPage = new PageImpl<>(List.of(responseDTO));
//
//        //given
//        BDDMockito.given(tilService.readUserTil(any(), anyLong())).willReturn(responseDTOPage);
//
//        //when
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1/tils")
//                .with(SecurityMockMvcRequestPostProcessors.csrf()));
//        //then
//        result.andExpect(MockMvcResultMatchers.status().isOk());
//    }
//}