package com.pray2you.p2uhomepage.domain.reply.controller;

import com.pray2you.p2uhomepage.auth.WithMockCustomUser;
import com.pray2you.p2uhomepage.domain.post.controller.PostController;
import com.pray2you.p2uhomepage.domain.post.dto.response.CreatePostResponseDTO;
import com.pray2you.p2uhomepage.domain.reply.dto.request.CreateReplyRequestDTO;
import com.pray2you.p2uhomepage.domain.reply.dto.response.CreateReplyResponseDTO;
import com.pray2you.p2uhomepage.domain.reply.dto.response.DeleteReplyResponseDTO;
import com.pray2you.p2uhomepage.domain.reply.dto.response.ReadReplyResponseDTO;
import com.pray2you.p2uhomepage.domain.reply.dto.response.UpdateReplyResponseDTO;
import com.pray2you.p2uhomepage.domain.reply.service.ReplyService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@WebMvcTest(controllers = ReplyController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {WebSecurityConfigure.class, JwtAuthenticationFilter.class})
        })
class ReplyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReplyService replyService;

//    @Test
//    @WithMockCustomUser
//    @DisplayName("## 댓글 등록 컨트롤러 테스트 ##")
//    void createReply() throws Exception {
//
//        StringBuilder replyCreateRequestJson = new StringBuilder();
//        replyCreateRequestJson.append("{")
//                .append("\"content\": \"내용이 없습니다.\"")
//                .append("}");
//
//        CreateReplyResponseDTO responseDTO = CreateReplyResponseDTO.builder()
//                .replyId(1L)
//                .postId(1L)
//                .userId(1L)
//                .username("gildong")
//                .content("내용이 없습니다.")
//                .createdDate(LocalDateTime.now())
//                .build();
//
//        //given
//        BDDMockito.given(replyService.createReply(anyLong(), anyLong(), any())).willReturn(responseDTO);
//
//        String json = replyCreateRequestJson.toString();
//        //when
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/posts/1/replies")
//                .with(SecurityMockMvcRequestPostProcessors.csrf())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json));
//
//        //then
//        result.andExpect(MockMvcResultMatchers.status().isOk());
//
//    }
//
//    @Test
//    @WithMockCustomUser
//    @DisplayName("## 댓글 수정 컨트롤러 테스트 ##")
//    void updateReply() throws Exception {
//
//        StringBuilder replyUpdateRequestJson = new StringBuilder();
//        replyUpdateRequestJson.append("{")
//                .append("\"content\": \"수정이 완료되었습니다.\"")
//                .append("}");
//
//        UpdateReplyResponseDTO responseDTO = UpdateReplyResponseDTO.builder()
//                .replyId(1L)
//                .postId(1L)
//                .userId(1L)
//                .username("gildong")
//                .content("수정이 완료되었습니다.")
//                .createdDate(LocalDateTime.now())
//                .modifiedDate(LocalDateTime.now())
//                .build();
//
//
//        //given
//        BDDMockito.given(replyService.updateReply(anyLong(), anyLong(), anyLong(), any())).willReturn(responseDTO);
//
//        String json = replyUpdateRequestJson.toString();
//        //when
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/api/posts/1/replies/1")
//                .with(SecurityMockMvcRequestPostProcessors.csrf())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json));
//
//        //then
//        result.andExpect(MockMvcResultMatchers.status().isOk());
//
//    }
//
//    @Test
//    @WithMockCustomUser
//    @DisplayName("## 댓글 삭제 컨트롤러 테스트 ##")
//    void deleteReply() throws Exception{
//        DeleteReplyResponseDTO responseDTO = DeleteReplyResponseDTO.builder()
//                .replyId(1L)
//                .postId(1L)
//                .userId(1L)
//                .username("gildong")
//                .content("수정이 완료되었습니다.")
//                .createdDate(LocalDateTime.now())
//                .deletedDate(LocalDateTime.now())
//                .build();
//
//        //given
//        BDDMockito.given(replyService.deleteReply(anyLong(), anyLong(), anyLong())).willReturn(responseDTO);
//
//        //when
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/posts/1/replies/1")
//                .with(SecurityMockMvcRequestPostProcessors.csrf()));
//
//        //then
//        result.andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    @WithMockCustomUser
//    @DisplayName("## 댓글 조회 컨트롤러 테스트 ##")
//    void readReply() throws Exception {
//        ReadReplyResponseDTO responseDTO = ReadReplyResponseDTO.builder()
//                .replyId(1L)
//                .postId(1L)
//                .userId(1L)
//                .username("gildong")
//                .content("수정이 완료되었습니다.")
//                .createdDate(LocalDateTime.now())
//                .modifiedDate(LocalDateTime.now())
//                .build();
//
//        //given
//        BDDMockito.given(replyService.readReply(anyLong())).willReturn(responseDTO);
//
//        //when
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/replies/1")
//                .with(SecurityMockMvcRequestPostProcessors.csrf()));
//
//        //then
//        result.andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    @WithMockCustomUser
//    @DisplayName("## 게시물 댓글 조회 컨트롤러 테스트 ##")
//    void readPostReply() throws Exception {
//        ReadReplyResponseDTO responseDTO = ReadReplyResponseDTO.builder()
//                .replyId(1L)
//                .postId(1L)
//                .userId(1L)
//                .username("gildong")
//                .content("수정이 완료되었습니다.")
//                .createdDate(LocalDateTime.now())
//                .modifiedDate(LocalDateTime.now())
//                .build();
//
//        Page<ReadReplyResponseDTO> responseDTOPage = new PageImpl<>(List.of(responseDTO));
//
//        //given
//        BDDMockito.given(replyService.readAllPostReply(any(), anyLong())).willReturn(responseDTOPage);
//
//        //when
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/posts/1/replies")
//                .with(SecurityMockMvcRequestPostProcessors.csrf()));
//
//        //then
//        result.andExpect(MockMvcResultMatchers.status().isOk());
//    }
}