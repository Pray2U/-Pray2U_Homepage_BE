package com.pray2you.p2uhomepage.domain.user.service;

import com.pray2you.p2uhomepage.domain.memberapproval.service.MemberApprovalService;
import com.pray2you.p2uhomepage.domain.model.ApprovalStatus;
import com.pray2you.p2uhomepage.domain.user.dto.request.CreateUserByAdditionalInfoRequestDTO;
import com.pray2you.p2uhomepage.domain.user.dto.request.UpdateUserRequestDTO;
import com.pray2you.p2uhomepage.domain.user.dto.request.UpdateUserRoleRequestDTO;
import com.pray2you.p2uhomepage.domain.user.dto.response.*;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import com.pray2you.p2uhomepage.domain.user.repository.UserRepository;
import com.pray2you.p2uhomepage.global.exception.errorcode.UserErrorCode;
import com.pray2you.p2uhomepage.global.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MemberApprovalService memberApprovalService;

    @Transactional
    public CreateUserByAdditionalInfoResponseDTO createUserByAdditionalInfo(
            Long userId, CreateUserByAdditionalInfoRequestDTO requestDTO) {
        User user = userRepository.findByIdAndDeleted(userId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_USER_EXCEPTION));
        User addInfoUser = requestDTO.toEntity(user);
        User updatedUser = userRepository.save(addInfoUser);

        //멤버 승인 조회에서 가입됨으로 표시
        memberApprovalService.updateApprovalStatus(user.getGithubId(), ApprovalStatus.JOINED);

        return CreateUserByAdditionalInfoResponseDTO.toDTO(updatedUser);
    }

    public DeleteUserResponseDTO deleteUser(Long userId) {
        User user = userRepository.findByIdAndDeleted(userId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_USER_EXCEPTION));
        user.delete();
        User deletedUser = userRepository.save(user);
        return DeleteUserResponseDTO.toDTO(deletedUser);
    }

    public UpdateUserResponseDTO updateUser(Long userId, UpdateUserRequestDTO requestDTO) {
        User user = userRepository.findByIdAndDeleted(userId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_USER_EXCEPTION));
        User updateUser = requestDTO.toEntity(user);
        User updatedUser = userRepository.save(updateUser);
        return UpdateUserResponseDTO.toDTO(updatedUser);
    }

    public ReadUserInfoResponseDTO readUserInfo(Long userId) {
        User user = userRepository.findByIdAndDeleted(userId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_USER_EXCEPTION));
        return ReadUserInfoResponseDTO.toDTO(user);
    }

    public Page<ReadUserInfoResponseDTO> readAllUser(Pageable pageable) {
        Page<User> users = userRepository.findByDeleted(pageable, false);
        return users
                .map(ReadUserInfoResponseDTO::toDTO);
    }

    public UpdateRoleResponseDTO updateRole(String githubId, UpdateUserRoleRequestDTO requestDTO) {
        User user = userRepository.findByGithubIdAndDeleted(githubId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_USER_EXCEPTION));
            User updateUser = user.updateRole(requestDTO.getRole());
        User updatedUser = userRepository.save(updateUser);
        return UpdateRoleResponseDTO.toDTO(updatedUser);
    }
}
