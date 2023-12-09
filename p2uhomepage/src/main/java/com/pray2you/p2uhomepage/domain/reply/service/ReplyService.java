package com.pray2you.p2uhomepage.domain.reply.service;

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
import com.pray2you.p2uhomepage.domain.user.enumeration.Role;
import com.pray2you.p2uhomepage.domain.user.repository.UserRepository;
import com.pray2you.p2uhomepage.global.exception.RestApiException;
import com.pray2you.p2uhomepage.global.exception.errorcode.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ReplyRepository replyRepository;

    public CreateReplyResponseDTO createReply(long userId, long postId, CreateReplyRequestDTO requestDTO) {

        User user = findUser(userId);

        Post post = findPost(postId);

        Reply reply = requestDTO.toEntity(user, post);
        Reply savedReply = replyRepository.save(reply);

        return CreateReplyResponseDTO.toDTO(savedReply);
    }

    public UpdateReplyResponseDTO updateReply(long userId, long postId, long replyId, UpdateReplyRequestDTO requestDTO) {
        User user = findUser(userId);

        Reply reply = replyRepository.findByIdAndUserAndDeleted(replyId, user, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_REPLY_EXCEPTION));

        //요청 온 게시글아이디와 댓글의 게시글 아이디가 일치하는지
        if (!isEqualReplyPostIdAndRequestPostId(reply, postId)) {
            throw new RestApiException(UserErrorCode.NOT_MATCH_POST_EXCEPTION);
        }

        Reply updateReply = requestDTO.toEntity(reply);
        Reply updatedReply = replyRepository.save(updateReply);

        return UpdateReplyResponseDTO.toDTO(updatedReply);
    }

    public DeleteReplyResponseDTO deleteReply(long userId, long postId, long replyId) {
        User user = findUser(userId);

        //운영진일 경우, 본인 작성 댓글 외의 댓글도 삭제 가능
        Reply reply;
        if (user.getRole() == Role.ROLE_ADMIN) {
            reply = replyRepository.findByIdAndDeleted(replyId, false)
                    .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_REPLY_EXCEPTION));
        } else {
            reply = replyRepository.findByIdAndUserAndDeleted(replyId, user, false)
                    .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_REPLY_EXCEPTION));
        }

        //요청 온 게시글아이디와 댓글의 게시글 아이디가 일치하는지
        if (!isEqualReplyPostIdAndRequestPostId(reply, postId)) {
            throw new RestApiException(UserErrorCode.NOT_MATCH_POST_EXCEPTION);
        }


        reply.delete();
        Reply deletedReply = replyRepository.save(reply);
        return DeleteReplyResponseDTO.toDTO(deletedReply);
    }

    public ReadReplyResponseDTO readReply(long replyId) {

        Reply reply = replyRepository.findByIdAndDeleted(replyId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_REPLY_EXCEPTION));

        return ReadReplyResponseDTO.toDTO(reply);
    }

    public Page<ReadReplyResponseDTO> readAllPostReply(Pageable pageable, long postId) {

        Post post = findPost(postId);

        Page<Reply> replyPage = replyRepository.findAllByPostAndDeleted(pageable, post, false);

        return replyPage.map(ReadReplyResponseDTO::toDTO);
    }

    private User findUser(long userId) {
        return userRepository.findByIdAndDeleted(userId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_USER_EXCEPTION));
    }

    private Post findPost(long postId) {
        return postRepository.findByIdAndDeleted(postId, false)
                .orElseThrow(()-> new RestApiException(UserErrorCode.NOT_EXIST_POST_EXCEPTION));
    }

    private boolean isEqualReplyPostIdAndRequestPostId(Reply reply, long postId) {
        return reply.getPost().getId() == postId;
    }
}
