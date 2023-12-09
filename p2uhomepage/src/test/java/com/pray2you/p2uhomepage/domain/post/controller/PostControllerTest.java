package com.pray2you.p2uhomepage.domain.post.controller;

import com.pray2you.p2uhomepage.auth.WithMockCustomUser;
import com.pray2you.p2uhomepage.domain.post.dto.response.CreatePostResponseDTO;
import com.pray2you.p2uhomepage.domain.post.dto.response.DeletePostResponseDTO;
import com.pray2you.p2uhomepage.domain.post.dto.response.ReadPostResponseDTO;
import com.pray2you.p2uhomepage.domain.post.dto.response.UpdatePostResponseDTO;
import com.pray2you.p2uhomepage.domain.post.service.PostService;
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
import static org.mockito.ArgumentMatchers.anyLong;

@WebMvcTest(controllers = PostController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {WebSecurityConfigure.class, JwtAuthenticationFilter.class})
        })
class PostControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private PostService postService;
//
//    @Test
//    @WithMockCustomUser
//    @DisplayName("## 공지사항 등록 컨트롤러 테스트 ##")
//    void createPost() throws Exception {
//
//        StringBuilder postCreateRequestJson = new StringBuilder();
//        postCreateRequestJson.append("{")
//                .append("\"title\": \"공지사항입니다.\",")
//                .append("\"content\": \"내용이 없습니다.\",")
//                .append("\"fileUrl\": \"file.com/1\"")
//                .append("}");
//
//        CreatePostResponseDTO responseDTO = new CreatePostResponseDTO(
//                1L,
//                "공지사항입니다.",
//                1L,
//                "gildong hong",
//                "내용이 없습니다.",
//                "file.com/1",
//                LocalDateTime.now()
//        );
//
//        //given
//        BDDMockito.given(postService.createPost(anyLong(), any())).willReturn(responseDTO);
//
//        String json = postCreateRequestJson.toString();
//        //when
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/posts")
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
//    @DisplayName("## 공지사항 수정 컨트롤러 테스트 ##")
//    void updatePost() throws Exception {
//
//        StringBuilder postUpdateRequestJson = new StringBuilder();
//        postUpdateRequestJson.append("{")
//                .append("\"title\": \"공지사항입니다.\",")
//                .append("\"content\": \"내용이 없습니다.\",")
//                .append("\"fileUrl\": \"file.com/1\"")
//                .append("}");
//
//        UpdatePostResponseDTO responseDTO = new UpdatePostResponseDTO(
//                1L,
//                "공지사항입니다.",
//                1L,
//                "gildong hong",
//                "내용이 없습니다.",
//                "file.com/1",
//                LocalDateTime.now(),
//                LocalDateTime.now()
//        );
//
//        //given
//        BDDMockito.given(postService.updatePost(anyLong(), any())).willReturn(responseDTO);
//
//        String json = postUpdateRequestJson.toString();
//        //when
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/api/posts/1/")
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
//    @DisplayName("## 공지사항 삭제 컨트롤러 테스트 ##")
//    void deletePost() throws Exception {
//
//        DeletePostResponseDTO responseDTO = new DeletePostResponseDTO(
//                1L,
//                "공지사항입니다.",
//                1L,
//                "gildong hong",
//                "내용이 없습니다.",
//                "file.com/1",
//                LocalDateTime.now(),
//                LocalDateTime.now()
//        );
//
//        //given
//        BDDMockito.given(postService.deletePost(anyLong())).willReturn(responseDTO);
//
//        //when
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/posts/1/")
//                .with(SecurityMockMvcRequestPostProcessors.csrf()));
//
//        //then
//        result.andExpect(MockMvcResultMatchers.status().isOk());
//    }

//    @Test
//    @WithMockCustomUser
//    @DisplayName("## 공지사항 조회 컨트롤러 테스트 ##")
//    void readPost() throws Exception {
//        ReadPostResponseDTO responseDTO = new ReadPostResponseDTO(
//                1L,
//                "공지사항입니다.",
//                1L,
//                "gildong hong",
//                "내용이 없습니다.",
//                "content",
//                "file.com/1",
//                LocalDateTime.now(),
//                LocalDateTime.now()
//        );
//
//        //given
//        BDDMockito.given(postService.readPost(anyLong())).willReturn(responseDTO);
//
//        //when
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/posts/1/")
//                .with(SecurityMockMvcRequestPostProcessors.csrf()));
//
//        //then
//        result.andExpect(MockMvcResultMatchers.status().isOk());
//    }

//    @Test
//    @WithMockCustomUser
//    @DisplayName("## 전체 공지사항 조회 컨트롤러 테스트 ##")
//    void readAllPost() throws Exception {
//        ReadPostResponseDTO responseDTO = new ReadPostResponseDTO(
//                1L,
//                "공지사항입니다.",
//                1L,
//                "gildong hong",
//                "내용이 없습니다.",
//                "content",
//                "file.com/1",
//                LocalDateTime.now(),
//                LocalDateTime.now()
//        );
//
//        Page<ReadPostResponseDTO> responseDTOPage = new PageImpl<>(List.of(responseDTO));
//
//        //given
//        BDDMockito.given(postService.readAllPost(any())).willReturn(responseDTOPage);
//
//        //when
//        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/posts")
//                .with(SecurityMockMvcRequestPostProcessors.csrf()));
//
//        //then
//        result.andExpect(MockMvcResultMatchers.status().isOk());
//    }
}