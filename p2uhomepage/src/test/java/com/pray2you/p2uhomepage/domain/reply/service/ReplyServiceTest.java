package com.pray2you.p2uhomepage.domain.reply.service;

import com.google.gson.Gson;
import com.pray2you.p2uhomepage.domain.user.enumeration.Role;
import com.pray2you.p2uhomepage.domain.post.entity.Post;
import com.pray2you.p2uhomepage.domain.post.repository.PostRepository;
import com.pray2you.p2uhomepage.domain.reply.dto.request.CreateReplyRequestDTO;
import com.pray2you.p2uhomepage.domain.reply.dto.request.UpdateReplyRequestDTO;
import com.pray2you.p2uhomepage.domain.reply.dto.response.CreateReplyResponseDTO;
import com.pray2you.p2uhomepage.domain.reply.dto.response.DeleteReplyResponseDTO;
import com.pray2you.p2uhomepage.domain.reply.dto.response.ReadReplyResponseDTO;
import com.pray2you.p2uhomepage.domain.reply.dto.response.UpdateReplyResponseDTO;
import com.pray2you.p2uhomepage.domain.reply.entity.Reply;
import com.pray2you.p2uhomepage.domain.reply.repository.ReplyRepository;
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
class ReplyServiceTest {

//    @Mock
//    private PostRepository postRepository;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private ReplyRepository replyRepository;
//
//    @InjectMocks
//    private ReplyService replyService;
//
//    @Test
//    @DisplayName("## 댓글 등록 서비스 테스트 ##")
//    void createReply() {
//
//        StringBuilder replyCreateRequestJson = new StringBuilder();
//        replyCreateRequestJson.append("{")
//                .append("\"content\": \"내용이 없습니다.\"")
//                .append("}");
//
//        CreateReplyRequestDTO requestDTO = new Gson()
//                .fromJson(replyCreateRequestJson.toString(), CreateReplyRequestDTO.class);
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
//        Reply reply = new Reply(post, user, "내용이 없습니다.");
//
//        CreateReplyResponseDTO responseDTO = CreateReplyResponseDTO.toDTO(reply);
//
//        //given
//        BDDMockito.given(userRepository.findByIdAndDeleted(anyLong(), anyBoolean())).willReturn(Optional.of(user));
//        BDDMockito.given(postRepository.findByIdAndDeleted(anyLong(), anyBoolean())).willReturn(Optional.of(post));
//        BDDMockito.given(replyRepository.save(any())).willReturn(reply);
//
//        //when
//        CreateReplyResponseDTO result = replyService.createReply(user.getId(), post.getId(), requestDTO);
//
//        //then
//        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTO);
//
//    }
//
////    @Test
////    @DisplayName("## 댓글 수정 서비스 테스트 ##")
////    void updateReply() {
////
////        StringBuilder replyUpdateRequestJson = new StringBuilder();
////        replyUpdateRequestJson.append("{")
////                .append("\"content\": \"내용이 없습니다.\"")
////                .append("}");
////
////        UpdateReplyRequestDTO requestDTO = new Gson()
////                .fromJson(replyUpdateRequestJson.toString(), UpdateReplyRequestDTO.class);
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
////        Reply reply = new Reply(post, user, "내용이 없습니다.");
////
////        UpdateReplyResponseDTO responseDTO = UpdateReplyResponseDTO.toDTO(reply);
////
////        //given
////        BDDMockito.given(userRepository.findByIdAndDeleted(anyLong(), anyBoolean())).willReturn(Optional.of(user));
////        BDDMockito.given(replyRepository.findByIdAndDeleted(anyLong(), anyBoolean())).willReturn(Optional.of(reply));
////        BDDMockito.given(replyRepository.save(any())).willReturn(reply);
////
////        //when
////        UpdateReplyResponseDTO result = replyService.updateReply(user.getId(), post.getId(), reply.getId(), requestDTO);
////
////        //then
////        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTO);
////
////    }
////
////    @Test
////    @DisplayName("## 댓글 삭제 서비스 테스트 ##")
////    void deleteReply() {
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
////        Reply reply = new Reply(post, user, "내용이 없습니다.");
////
////        DeleteReplyResponseDTO responseDTO = DeleteReplyResponseDTO.toDTO(reply);
////
////        //given
////        BDDMockito.given(userRepository.findByIdAndDeleted(anyLong(), anyBoolean())).willReturn(Optional.of(user));
////        BDDMockito.given(replyRepository.findByIdAndDeleted(anyLong(), anyBoolean())).willReturn(Optional.of(reply));
////        BDDMockito.given(replyRepository.save(any())).willReturn(reply);
////
////        //when
////        DeleteReplyResponseDTO result = replyService.deleteReply(user.getId(), post.getId(), reply.getId());
////
////        //then
////        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTO);
////
////    }
//
//    @Test
//    @DisplayName("## 댓글 조회 서비스 테스트 ##")
//    void readReply() {
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
//        Reply reply = new Reply(post, user, "내용이 없습니다.");
//
//        ReadReplyResponseDTO responseDTO = ReadReplyResponseDTO.toDTO(reply);
//
//        //given
//        BDDMockito.given(replyRepository.findByIdAndDeleted(anyLong(), anyBoolean())).willReturn(Optional.of(reply));
//
//        //when
//        ReadReplyResponseDTO result = replyService.readReply(reply.getId());
//
//        //then
//        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTO);
//    }
//
//    @Test
//    @DisplayName("## 게시물 댓글 조회 서비스 테스트 ##")
//    void readAllPostReply() {
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
//        Reply reply = new Reply(post, user, "내용이 없습니다.");
//
//        ReadReplyResponseDTO responseDTO = ReadReplyResponseDTO.toDTO(reply);
//        Page<ReadReplyResponseDTO> responseDTOPage = new PageImpl<>(List.of(responseDTO));
//
//        //given
//        BDDMockito.given(replyRepository.findAllByPostAndDeleted(any(), any(), anyBoolean())).willReturn(new PageImpl<>(List.of(reply)));
//        BDDMockito.given(postRepository.findByIdAndDeleted(anyLong(), anyBoolean())).willReturn(Optional.of(post));
//
//        //when
//        Page<ReadReplyResponseDTO> result = replyService.readAllPostReply(PageRequest.of(0, 20), 1L);
//
//        //then
//        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(responseDTOPage);
//    }
}