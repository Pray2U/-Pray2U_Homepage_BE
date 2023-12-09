package com.pray2you.p2uhomepage.domain.post.controller;

import com.pray2you.p2uhomepage.domain.post.dto.request.CreatePostRequestDTO;
import com.pray2you.p2uhomepage.domain.post.dto.request.UpdatePostRequestDTO;
import com.pray2you.p2uhomepage.domain.post.dto.response.*;
import com.pray2you.p2uhomepage.domain.post.service.PostService;
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
public class PostController {

    private final PostService postService;
    @PostMapping("api/admin/posts")
    public ResponseEntity<Map<String, Object>> createPost(Authentication authentication, @Validated @RequestBody CreatePostRequestDTO createPostRequestDTO) {
        Long userId = ((CustomUserDetails)authentication.getPrincipal()).getId();
        CreatePostResponseDTO responseDTO = postService.createPost(userId, createPostRequestDTO);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "성공적으로 게시물이 등록되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("api/admin/posts/{postId}")
    public ResponseEntity<Map<String, Object>> updatePost(Authentication authentication, @PathVariable long postId, @RequestBody @Validated UpdatePostRequestDTO requestDTO) {
        Long userId = ((CustomUserDetails)authentication.getPrincipal()).getId();
        UpdatePostResponseDTO responseDTO = postService.updatePost(userId, postId, requestDTO);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "성공적으로 게시물이 수정되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("api/admin/posts/{postId}")
    public ResponseEntity<Map<String, Object>> deletePost(@PathVariable long postId) {
        DeletePostResponseDTO responseDTO = postService.deletePost(postId);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "성공적으로 게시물이 삭제되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("api/posts/{postId}")
    public ResponseEntity<Map<String, Object>> readPost(@PathVariable long postId) {
        ReadPostResponseDTO responseDTO = postService.readPost(postId);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "성공적으로 게시물이 조회되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("api/posts")
    public ResponseEntity<Map<String, Object>> readAllPost(Pageable pageable)  {
        Page<ReadAllPostResponseDTO> responseDTO = postService.readAllPost(pageable);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "성공적으로 게시물이 조회되었습니다.");
        result.put("data", responseDTO);
        return ResponseEntity.ok().body(result);
    }
}
