package com.pray2you.p2uhomepage.domain.point.service;

import com.pray2you.p2uhomepage.domain.point.dto.response.ReadTotalPointResponseDTO;
import com.pray2you.p2uhomepage.domain.point.entity.TotalPoint;
import com.pray2you.p2uhomepage.domain.point.repository.TotalPointRepository;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import com.pray2you.p2uhomepage.domain.user.repository.UserRepository;
import com.pray2you.p2uhomepage.global.exception.RestApiException;
import com.pray2you.p2uhomepage.global.exception.errorcode.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TotalPointService {

    private final TotalPointRepository totalPointRepository;
    private final UserRepository userRepository;

    public ReadTotalPointResponseDTO readTotalPoint(long userId) {

        User user = findUser(userId);

        TotalPoint totalPoint = totalPointRepository.findByUser(user)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_TOTAL_POINT_EXCEPTION));

        return ReadTotalPointResponseDTO.toDTO(totalPoint);
    }

    public User findUser(long userId) {
        return userRepository.findByIdAndDeleted(userId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_USER_EXCEPTION));
    }
}
