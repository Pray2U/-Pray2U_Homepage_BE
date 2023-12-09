package com.pray2you.p2uhomepage.domain.post.service;

import com.pray2you.p2uhomepage.domain.post.dto.request.CreatePostRequestDTO;
import com.pray2you.p2uhomepage.domain.post.dto.request.UpdatePostRequestDTO;
import com.pray2you.p2uhomepage.domain.post.dto.response.*;
import com.pray2you.p2uhomepage.domain.post.entity.Post;
import com.pray2you.p2uhomepage.domain.post.repository.PostRepository;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import com.pray2you.p2uhomepage.domain.user.repository.UserRepository;
import com.pray2you.p2uhomepage.global.exception.RestApiException;
import com.pray2you.p2uhomepage.global.exception.errorcode.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CreatePostResponseDTO createPost(long userId, CreatePostRequestDTO requestDTO) {

        User user = findUser(userId);

        Post post = requestDTO.toEntity(user);
        Post savedPost = postRepository.save(post);

        return CreatePostResponseDTO.toDTO(savedPost);
    }

    public UpdatePostResponseDTO updatePost(long userId, long postId, UpdatePostRequestDTO requestDTO) {

        User user = findUser(userId);

        Post post = postRepository.findByIdAndUserAndDeleted(postId, user, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_POST_EXCEPTION));

        Post updatePost = requestDTO.toEntity(post);
        Post updatedPost = postRepository.save(updatePost);

        return UpdatePostResponseDTO.toDTO(updatedPost);
    }

    public DeletePostResponseDTO deletePost(long postId) {
        Post post = postRepository.findByIdAndDeleted(postId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_POST_EXCEPTION));

        post.delete();
        Post deletedPost = postRepository.save(post);

        return DeletePostResponseDTO.toDTO(deletedPost);
    }

    public ReadPostResponseDTO readPost(long postId) {
        Post post = postRepository.findByIdAndDeleted(postId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_POST_EXCEPTION));

        return ReadPostResponseDTO.toDTO(post);
    }

    public Page<ReadAllPostResponseDTO> readAllPost(Pageable pageable) {
        Page<Post> posts = postRepository.findByDeleted(pageable, false);

        return posts.map(ReadAllPostResponseDTO::toDTO);
    }

    private User findUser(long userId) {
        return userRepository.findByIdAndDeleted(userId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_USER_EXCEPTION));
    }
}
