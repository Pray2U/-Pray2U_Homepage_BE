package com.pray2you.p2uhomepage.domain.post.service;

import com.google.gson.Gson;
import com.pray2you.p2uhomepage.domain.post.dto.response.*;
import com.pray2you.p2uhomepage.domain.user.enumeration.Role;
import com.pray2you.p2uhomepage.domain.post.dto.request.CreatePostRequestDTO;
import com.pray2you.p2uhomepage.domain.post.dto.request.UpdatePostRequestDTO;
import com.pray2you.p2uhomepage.domain.post.entity.Post;
import com.pray2you.p2uhomepage.domain.post.repository.PostRepository;
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

import static org.mockito.ArgumentMatchers.*;


@ExtendWith(MockitoExtension.class)
class PostServiceTest {
//
//    @Mock
//    private PostRepository postRepository;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private PostService postService;
//
//
//    @Test
//    @DisplayName("## 공지사항 등록 서비스 테스트 ##")
//    void createPost() {
//
//        StringBuilder postCreateRequestJson = new StringBuilder();
//        postCreateRequestJson.append("{")
//                .append("\"title\": \"공지사항입니다.\",")
//                .append("\"content\": \"내용이 없습니다.\",")
//                .append("\"fileUrl\": \"file.com/1\"")
//                .append("}");
//
//        CreatePostRequestDTO requestDTO = new Gson()
//                .fromJson(postCreateRequestJson.toString(), CreatePostRequestDTO.class);
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
//        Post post = Post.builder()
//                .title("공지사항입니다.")
//                .content("내용이 없습니다.")
//                .fileUrl("file.com/1")
//                .user(user)
//                .build();
//
//        CreatePostResponseDTO responseDTO =  CreatePostResponseDTO.toDTO(post);
//
//        //given
//        BDDMockito.given(userRepository.findByIdAndDeleted(anyLong(), anyBoolean())).willReturn(Optional.of(user));
//        BDDMockito.given(postRepository.save(any())).willReturn(post);
//
//        //when
//        CreatePostResponseDTO result = postService.createPost(user.getId(), requestDTO);
//
//        //then
//        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTO);
//    }
//
////    @Test
////    @DisplayName("## 공지사항 수정 서비스 테스트 ##")
////    void updatePost() {
////
////        StringBuilder postUpdateRequestJson = new StringBuilder();
////        postUpdateRequestJson.append("{")
////                .append("\"title\": \"공지사항입니다.\",")
////                .append("\"content\": \"내용이 없습니다.\",")
////                .append("\"fileUrl\": \"file.com/1\"")
////                .append("}");
////
////        UpdatePostRequestDTO requestDTO = new Gson()
////                .fromJson(postUpdateRequestJson.toString(), UpdatePostRequestDTO.class);
////
////        User user = User.builder()
////                .username("honggildong")
////                .email("gildong@gmail.com")
////                .phoneNumber("010-0000-0000")
////                .profileImgUrl("gildong.com/123rr")
////                .githubId("gildong Hong")
////                .role(Role.ROLE_USER)
////                .build();
////
////        Post post = Post.builder()
////                .title("공지사항입니다.")
////                .content("내용이 없습니다.")
////                .fileUrl("file.com/1")
////                .user(user)
////                .build();
////
////        UpdatePostResponseDTO responseDTO =  UpdatePostResponseDTO.toDTO(post);
////
////        //given
////        BDDMockito.given(postRepository.findByIdAndDeleted(anyLong(), anyBoolean())).willReturn(Optional.of(post));
////        BDDMockito.given(postRepository.save(any())).willReturn(post);
////
////        //when
////        UpdatePostResponseDTO result = postService.updatePost(user.getId(), requestDTO);
////
////        //then
////        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTO);
////    }
//
//    @Test
//    @DisplayName("## 공지사항 삭제 서비스 테스트 ##")
//    void deletePost() {
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
//        Post post = Post.builder()
//                .title("공지사항입니다.")
//                .content("내용이 없습니다.")
//                .fileUrl("file.com/1")
//                .user(user)
//                .build();
//
//        DeletePostResponseDTO responseDTO = DeletePostResponseDTO.toDTO(post);
//
//        //given
//        BDDMockito.given(postRepository.findByIdAndDeleted(anyLong(), anyBoolean())).willReturn(Optional.of(post));
//        post.delete();
//        BDDMockito.given(postRepository.save(any())).willReturn(post);
//
//        //when
//        DeletePostResponseDTO result = postService.deletePost(user.getId());
//
//        //then
//        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTO);
//    }
//
//    @Test
//    @DisplayName("## 공지사항 조회 서비스 테스트 ##")
//    void readPost() {
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
//        Post post = Post.builder()
//                .title("공지사항입니다.")
//                .content("내용이 없습니다.")
//                .fileUrl("file.com/1")
//                .user(user)
//                .build();
//
//        ReadPostResponseDTO responseDTO = ReadPostResponseDTO.toDTO(post);
//
//        //given
//        BDDMockito.given(postRepository.findByIdAndDeleted(anyLong(), anyBoolean())).willReturn(Optional.of(post));
//
//        //when
//        ReadPostResponseDTO result = postService.readPost(post.getId());
//
//        //then
//        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTO);
//    }

//    @Test
//    @DisplayName("## 전체 공지사항 조회 서비스 테스트 ##")
//    void readAllPost() {
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
//        Post post = Post.builder()
//                .title("공지사항입니다.")
//                .content("내용이 없습니다.")
//                .fileUrl("file.com/1")
//                .user(user)
//                .build();
//
//
//        ReadPostResponseDTO responseDTO = ReadPostResponseDTO.toDTO(post);
//        Page<ReadPostResponseDTO> responseDTOPage = new PageImpl<>(List.of(responseDTO));
//
//        //given
//        BDDMockito.given(postRepository.findByDeleted(any(), anyBoolean())).willReturn(new PageImpl<>(List.of(post)));
//
//        //when
//        Page<ReadPostListResponseDTO> result = postService.readAllPost(PageRequest.of(0, 20));
//
//        //then
//        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTOPage);
//    }
}