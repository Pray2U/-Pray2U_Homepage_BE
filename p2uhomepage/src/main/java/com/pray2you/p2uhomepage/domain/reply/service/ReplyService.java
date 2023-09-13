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

        User user = userRepository.findByIdAndDeleted(userId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_USER_EXCEPTION));

        Post post = postRepository.findByIdAndDeleted(postId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_POST_EXCEPTION));

        Reply reply = requestDTO.toEntity(user, post);
        Reply savedReply = replyRepository.save(reply);

        return CreateReplyResponseDTO.toDTO(savedReply);
    }

    public UpdateReplyResponseDTO updateReply(long userId, long postId, long replyId, UpdateReplyRequestDTO requestDTO) {
        User user = userRepository.findByIdAndDeleted(userId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_USER_EXCEPTION));

        Reply reply = replyRepository.findByIdAndDeleted(replyId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_REPLY_EXCEPTION));

        if (!user.equals(reply.getUser())) {
            throw new RestApiException(UserErrorCode.ACCESS_DENIED);
        }

        if (reply.getPost().getId() != postId) {
            throw new RestApiException(UserErrorCode.NOT_MATCH_POST_EXCEPTION);
        }

        Reply updateReply = requestDTO.toEntity(reply);
        Reply updatedReply = replyRepository.save(updateReply);

        return UpdateReplyResponseDTO.toDTO(updatedReply);
    }

    public DeleteReplyResponseDTO deleteReply(long userId, long postId, long replyId) {
        User user = userRepository.findByIdAndDeleted(userId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_USER_EXCEPTION));

        Reply reply = replyRepository.findByIdAndDeleted(replyId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_REPLY_EXCEPTION));

        if (reply.getPost().getId() != postId) {
            throw new RestApiException(UserErrorCode.NOT_MATCH_POST_EXCEPTION);
        }

        if (!user.equals(reply.getUser())) {
            throw new RestApiException(UserErrorCode.ACCESS_DENIED);
        }

        Reply deletedReply = replyRepository.save(reply.delete());
        return DeleteReplyResponseDTO.toDTO(deletedReply);
    }

    public ReadReplyResponseDTO readReply(long replyId) {

        Reply reply = replyRepository.findByIdAndDeleted(replyId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_REPLY_EXCEPTION));

        return ReadReplyResponseDTO.toDTO(reply);
    }

    public Page<ReadReplyResponseDTO> readAllPostReply(Pageable pageable, long postId) {

        Post post = postRepository.findByIdAndDeleted(postId, false)
                .orElseThrow(()-> new RestApiException(UserErrorCode.NOT_EXIST_POST_EXCEPTION));

        Page<Reply> replyPage = replyRepository.findAllByPostAndDeleted(pageable, post, false);

        return replyPage.map(ReadReplyResponseDTO::toDTO);
    }
}
