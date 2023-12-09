package com.pray2you.p2uhomepage.domain.point.service;

import com.pray2you.p2uhomepage.domain.point.dto.response.ReadDetailPointResponseDTO;
import com.pray2you.p2uhomepage.domain.point.entity.DetailPoint;
import com.pray2you.p2uhomepage.domain.point.repository.DetailPointRepository;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import com.pray2you.p2uhomepage.domain.user.repository.UserRepository;
import com.pray2you.p2uhomepage.global.exception.RestApiException;
import com.pray2you.p2uhomepage.global.exception.errorcode.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetailPointService {

    private final DetailPointRepository detailPointRepository;
    private final UserRepository userRepository;

    public List<ReadDetailPointResponseDTO> readAllMyDetailPoint(long userId) {
        User user = userRepository.findByIdAndDeleted(userId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_USER_EXCEPTION));

        List<DetailPoint> detailPoints = detailPointRepository.findAllByUser(user);

        return detailPoints.stream().map(ReadDetailPointResponseDTO::toDTO).collect(Collectors.toList());
    }
}
