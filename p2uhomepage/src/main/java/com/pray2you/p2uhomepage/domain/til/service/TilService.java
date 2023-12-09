package com.pray2you.p2uhomepage.domain.til.service;

import com.pray2you.p2uhomepage.domain.point.enumeration.PointContent;
import com.pray2you.p2uhomepage.domain.point.event.PointAddEvent;
import com.pray2you.p2uhomepage.domain.til.dto.request.CreateTilRequestDTO;
import com.pray2you.p2uhomepage.domain.til.dto.request.UpdateTilRequestDTO;
import com.pray2you.p2uhomepage.domain.til.dto.response.CreateTilResponseDTO;
import com.pray2you.p2uhomepage.domain.til.dto.response.DeleteTilResponseDTO;
import com.pray2you.p2uhomepage.domain.til.dto.response.ReadTilResponseDTO;
import com.pray2you.p2uhomepage.domain.til.dto.response.UpdateTilResponseDTO;
import com.pray2you.p2uhomepage.domain.til.entity.Til;
import com.pray2you.p2uhomepage.domain.til.entity.TilRandomPoint;
import com.pray2you.p2uhomepage.domain.til.repository.TilRandomPointRepository;
import com.pray2you.p2uhomepage.domain.til.repository.TilRepository;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import com.pray2you.p2uhomepage.domain.user.enumeration.Role;
import com.pray2you.p2uhomepage.domain.user.repository.UserRepository;
import com.pray2you.p2uhomepage.global.exception.RestApiException;
import com.pray2you.p2uhomepage.global.exception.errorcode.UserErrorCode;
import com.pray2you.p2uhomepage.global.utils.RandomPointUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TilService {

    private final UserRepository userRepository;
    private final TilRepository tilRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final TilRandomPointRepository tilRandomPointRepository;

    @Transactional
    public CreateTilResponseDTO createTil(long userId, CreateTilRequestDTO requestDTO) {

        User user = findUser(userId);

        Til til = requestDTO.toEntity(user);

        //오늘 til을 이미 썼다면, 더 이상 쓸 수 없음
        if (haveAlreadyWriteTodayTil(user)) {
            throw new RestApiException(UserErrorCode.TIL_RESTRICTION_EXCEPTION);
        }

        // 포인트 증가 로직
        // 이번 주 전체 til이 없을 때, 랜덤포인트 레디스에 등록 및 포인트 등록
        // 이번 주 til이 있다면, 삭제되지 않은 til이 2개 이상일때는 포인트 등록 x
        //     ->  2개 미만일때는 레디스 인덱스참고하여 포인트 등록

        //이번 주 til 리스트
        LocalDateTime thisMonday = LocalDateTime.now().with(DayOfWeek.MONDAY).with(LocalTime.MIN);
        List<Til> tils = tilRepository.findByUserAndCreatedDateGreaterThanEqual(user, thisMonday);

        //이번 랜덤포인트 데이터 생성
        createWeekTilRandomPointData(user, tils);

        //삭제 되지 않은 til만 필터링
        tils = tils.stream().filter(t -> !t.isDeleted()).collect(Collectors.toList());

        int point = 0;

        //포인트 생성 및 포인트 증가 이벤트 생성
        if (isAvailableAddPoint(tils)) {
            point = getTilRandomPoint(userId, tils, false);
            applicationEventPublisher.publishEvent(new PointAddEvent(PointContent.TIL, point, user));
        }

        Til savedTil = tilRepository.save(til);

        return CreateTilResponseDTO.toDTO(savedTil, point);
    }

    public UpdateTilResponseDTO updateTil(long userId, long tilId, UpdateTilRequestDTO requestDTO) {
        User user = findUser(userId);

        Til til = tilRepository.findByIdAndUserAndDeleted(tilId, user, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_TIL_EXCEPTION));


        Til updateTil = requestDTO.toEntity(til);
        Til updatedTil = tilRepository.save(updateTil);

        return UpdateTilResponseDTO.toDTO(updatedTil);
    }

    @Transactional
    public DeleteTilResponseDTO deleteTil(long userId, long tilId) {
        User user = findUser(userId);

        Til til;
        //운영진일 경우, 본인 작성 Til 외의 Til도 삭제 가능
        if (user.getRole() == Role.ROLE_ADMIN) {
           til = tilRepository.findByIdAndDeleted(tilId, false)
                   .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_TIL_EXCEPTION));
        }else {
            til = tilRepository.findByIdAndUserAndDeleted(tilId, user, false)
                    .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_TIL_EXCEPTION));
        }

        //포인트 삭감 로직
        //삭제하려는 til이 이번주 til이며, 이번 주 삭제되지 않은 til이 두개 이하일때, 레디스 참고하여 포인트 삭감
        //이번 주 삭제되지 않은 til이 두개 초과일때, 포인트 삭감x
        LocalDateTime thisMonday = LocalDateTime.now().with(DayOfWeek.MONDAY).with(LocalTime.MIN);
        List<Til> tils = tilRepository.findAllByUserAndCreatedDateGreaterThanEqualAndDeleted(user, thisMonday, false);
        
        int point = 0;

        //포인트 처리 및 이벤트 생성
        if(doPointDecreaseWhenDeleteTil(til, tils)) {
            point = getTilRandomPoint(userId, tils, true);
            applicationEventPublisher.publishEvent(new PointAddEvent(PointContent.TIL, point, user));
        }
        til.delete();
        Til deletedTil = tilRepository.save(til);

        return DeleteTilResponseDTO.toDTO(deletedTil, point);
    }

    public Page<ReadTilResponseDTO> readAllTil(Pageable pageable) {
        Page<Til> allTilPage = tilRepository.findAllByDeleted(pageable, false);

        return allTilPage.map(ReadTilResponseDTO::toDTO);
    }

    public Page<ReadTilResponseDTO> readTils(Pageable pageable, String keyword) {
        if (Objects.equals(keyword, "")) {
            return readAllTil(pageable);
        }

        return searchTil(pageable, keyword);
    }

    public ReadTilResponseDTO readTil(long tilId) {
        Til til = tilRepository.findByIdAndDeleted(tilId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_TIL_EXCEPTION));

        return ReadTilResponseDTO.toDTO(til);
    }

    public Page<ReadTilResponseDTO> readUserTil(Pageable pageable, long userId) {
        User user = findUser(userId);

        Page<Til> tilPage = tilRepository.findAllByUserAndDeleted(pageable, user, false);

        return tilPage.map(ReadTilResponseDTO::toDTO);
    }

    public Page<ReadTilResponseDTO> searchTil(Pageable pageable, String keyword) {
        Page<Til> tilPage = tilRepository.findAllByTitleContainingOrContentContainingOrTagContaining(pageable, keyword);

        return tilPage.map(ReadTilResponseDTO::toDTO);
    }

    private void createRandomPoint(User user) {
        int randomPoint = RandomPointUtil.getRandomPoint(PointContent.TIL);
        int randomPoint2 = RandomPointUtil.getRandomPoint(PointContent.TIL);
        tilRandomPointRepository.save(new TilRandomPoint(user.getId(), randomPoint, randomPoint2));
    }

    private boolean isAvailableAddPoint(List<Til> tils) {
        return tils.size() < 2;
    }

    private boolean doPointDecreaseWhenDeleteTil(Til til, List<Til> tils) {
        LocalDateTime thisMonday = LocalDateTime.now().with(DayOfWeek.MONDAY).with(LocalTime.MIN);
        return til.getCreatedDate().isAfter(thisMonday) && tils.size() < 3;
    }

    private User findUser(long userId) {
        return userRepository.findByIdAndDeleted(userId, false)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_USER_EXCEPTION));
    }

    private boolean haveAlreadyWriteTodayTil(User user) {
        LocalDateTime today = LocalDateTime.now().with(LocalTime.MIN);
        return tilRepository.findOneByUserAndCreatedDateGreaterThanEqualAndDeleted(user, today, false) != null;
    }
    
    private void createWeekTilRandomPointData(User user, List<Til> tils) {
        //이번 주 til 랜덤포인트 목록 생성
        //til삭제 등록의 악용을 막기 위함
        if (tils.isEmpty()) {
            createRandomPoint(user);
        }
    }

    private int getTilRandomPoint(long userId, List<Til> tils, boolean delete) {
        TilRandomPoint randomPoint = tilRandomPointRepository.findById(userId)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_RANDOM_POINT_EXCEPTION));

        if (delete) {
            return tils.size() == 1 ? -randomPoint.getRandomPoint1() : -randomPoint.getRandomPoint2();
        }

        return tils.isEmpty() ? randomPoint.getRandomPoint1() : randomPoint.getRandomPoint2();
    }
}
