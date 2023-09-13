package com.pray2you.p2uhomepage.domain.reply.controller;

import com.pray2you.p2uhomepage.domain.reply.dto.request.CreateReplyRequestDTO;
import com.pray2you.p2uhomepage.domain.reply.dto.request.UpdateReplyRequestDTO;
import com.pray2you.p2uhomepage.domain.reply.dto.response.CreateReplyResponseDTO;
import com.pray2you.p2uhomepage.domain.reply.dto.response.DeleteReplyResponseDTO;
import com.pray2you.p2uhomepage.domain.reply.dto.response.ReadReplyResponseDTO;
import com.pray2you.p2uhomepage.domain.reply.dto.response.UpdateReplyResponseDTO;
import com.pray2you.p2uhomepage.domain.reply.service.ReplyService;
import com.pray2you.p2uhomepage.global.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("api/posts/{postId}/replies")
    ResponseEntity<Map<String, Object>> createReply(
            Authentication authentication,
            @PathVariable long postId,
            @Validated @RequestBody CreateReplyRequestDTO requestDTO) {

        long userId = ((CustomUserDetails)authentication.getPrincipal()).getId();

        CreateReplyResponseDTO responseDTO = replyService.createReply(userId, postId, requestDTO);

        Map<String, Object> result = new HashMap<>();
        result.put("msg", "성공적으로 댓글이 생성되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("api/posts/{postId}/replies/{replyId}")
    ResponseEntity<Map<String, Object>> updateReply(
            Authentication authentication,
            @PathVariable long postId,
            @PathVariable long replyId,
            @Validated @RequestBody UpdateReplyRequestDTO requestDTO
            ) {

        long userId = ((CustomUserDetails)authentication.getPrincipal()).getId();

        UpdateReplyResponseDTO responseDTO = replyService.updateReply(userId, postId, replyId, requestDTO);

        Map<String, Object> result = new HashMap<>();
        result.put("msg", "성공적으로 댓글이 수정되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("api/posts/{postId}/replies/{replyId}")
    ResponseEntity<Map<String, Object>> deleteReply(Authentication authentication, @PathVariable long postId, @PathVariable long replyId) {
        long userId = ((CustomUserDetails)authentication.getPrincipal()).getId();

        DeleteReplyResponseDTO responseDTO = replyService.deleteReply(userId, postId, replyId);

        Map<String, Object> result = new HashMap<>();
        result.put("msg", "성공적으로 댓글이 삭제되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("api/replies/{replyId}")
    ResponseEntity<Map<String, Object>> readReply(@PathVariable long replyId) {
        ReadReplyResponseDTO responseDTO = replyService.readReply(replyId);

        Map<String, Object> result = new HashMap<>();
        result.put("msg", "성공적으로 댓글이 조회되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("api/posts/{postId}/replies")
    ResponseEntity<Map<String, Object>> readPostReply(@PathVariable long postId, Pageable pageable) {
        Page<ReadReplyResponseDTO> responseDTOPage = replyService.readAllPostReply(pageable, postId);

        Map<String, Object> result = new HashMap<>();
        result.put("msg", "성공적으로 댓글이 조회되었습니다.");
        result.put("data", responseDTOPage);
        return ResponseEntity.ok().body(result);
    }


}
