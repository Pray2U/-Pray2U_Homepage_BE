package com.pray2you.p2uhomepage.domain.user.service;

import com.pray2you.p2uhomepage.domain.memberapproval.repository.MemberApprovalRepository;
import com.pray2you.p2uhomepage.domain.user.enumeration.Role;
import com.pray2you.p2uhomepage.domain.point.entity.TotalPoint;
import com.pray2you.p2uhomepage.domain.point.repository.TotalPointRepository;
import com.pray2you.p2uhomepage.domain.user.dto.request.CreateUserByAdditionalInfoRequestDTO;
import com.pray2you.p2uhomepage.domain.user.dto.request.UpdateUserRequestDTO;
import com.pray2you.p2uhomepage.domain.user.dto.request.UpdateUserRoleRequestDTO;
import com.pray2you.p2uhomepage.domain.user.dto.response.*;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import com.pray2you.p2uhomepage.domain.user.repository.UserRepository;
import com.pray2you.p2uhomepage.global.exception.errorcode.UserErrorCode;
import com.pray2you.p2uhomepage.global.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final TotalPointRepository totalPointRepository;
    private final UserRepository userRepository;
    @Value("${p2u.admin.developer1}")
    private String ADMIN_1;
    @Value("${p2u.admin.developer2}")
    private String ADMIN_2;
    private final MemberApprovalRepository memberApprovalRepository;

    @Transactional
    public CreateUserByAdditionalInfoResponseDTO createUserByAdditionalInfo(
            Long userId, CreateUserByAdditionalInfoRequestDTO requestDTO) {

        User user = findUser(userId);
        User addInfoUser = requestDTO.toEntity(user);

        //기본 어드민 유저일 경우 어드민으로 등록
        updateAdminUser(addInfoUser);

        User updatedUser = userRepository.save(addInfoUser);

        //유저 포인트 엔티티 생성
        createTotalPoint(user);
        
        return CreateUserByAdditionalInfoResponseDTO.toDTO(updatedUser);
    }

    @Transactional
    public DeleteUserResponseDTO deleteUser(Long userId) {
        User user = findUser(userId);
        user.delete();
        User deletedUser = userRepository.save(user);
        memberApprovalRepository.deleteByGithubId(user.getGithubId());
        return DeleteUserResponseDTO.toDTO(deletedUser);
    }

    public UpdateUserResponseDTO updateUser(Long userId, UpdateUserRequestDTO requestDTO) {
        User user = findUser(userId);
        User updateUser = requestDTO.toEntity(user);
        User updatedUser = userRepository.save(updateUser);
        return UpdateUserResponseDTO.toDTO(updatedUser);
    }

    public ReadUserInfoResponseDTO readUserInfo(Long userId) {
        User user = findUser(userId);
        return ReadUserInfoResponseDTO.toDTO(user);
    }

    public ReadMemberInfoResponseDTO readMember(Long userId) {
        User user = findUser(userId);
        return ReadMemberInfoResponseDTO.toDTO(user);
    }

    public Page<ReadMemberInfoResponseDTO> readAllUser(Pageable pageable) {
        Page<User> users = userRepository.findByDeleted(pageable, false);
        return users.map(ReadMemberInfoResponseDTO::toDTO);
    }

    public UpdateRoleResponseDTO updateRole(long userId, UpdateUserRoleRequestDTO requestDTO) {
        User user = findUser(userId);
        user.updateRole(requestDTO.getRole());
        User updatedUser = userRepository.save(user);
        return UpdateRoleResponseDTO.toDTO(updatedUser);
    }

    private void createTotalPoint(User user) {
        if(!totalPointRepository.existsByUser(user)){
            totalPointRepository.save(new TotalPoint(user));
        }
    }

    private User findUser(long userId) {
        return userRepository.findByIdAndDeleted(userId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_USER_EXCEPTION));
    }

    private void updateAdminUser(User user) {
        if(user.getGithubId().equals(ADMIN_1) || user.getGithubId().equals(ADMIN_2)) {
            user.updateRole(Role.ROLE_ADMIN);
        }
    }
}
