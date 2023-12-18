package com.pray2you.p2uhomepage.domain.point.service;

import com.pray2you.p2uhomepage.domain.point.dao.RankDao;
import com.pray2you.p2uhomepage.domain.point.dto.response.ReadCurrentRankResponseDTO;
import com.pray2you.p2uhomepage.domain.point.dto.response.ReadRankResponseDTO;
import com.pray2you.p2uhomepage.domain.point.entity.Rank;
import com.pray2you.p2uhomepage.domain.point.repository.RankRepository;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import com.pray2you.p2uhomepage.domain.user.repository.UserRepository;
import com.pray2you.p2uhomepage.global.exception.RestApiException;
import com.pray2you.p2uhomepage.global.exception.errorcode.UserErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RankService {

    private final UserRepository userRepository;
    private final RankRepository rankRepository;
    private final RankDao rankDao;

    @Scheduled(cron = "0 50 23 * * 0")
    @Transactional
    public void createAllUserRank() {
        log.info("이번 주 랭크 포인트 데이터를 초기화 합니다.");
        List<User> users = userRepository.findAllByDeleted(false);

        List<Rank> ranks = new ArrayList<>();

        for (User user : users) {
            Rank rank = createRankByUser(user);
            ranks.add(rank);
        }

        rankRepository.saveAll(ranks);
        log.info("이번 주 랭크 포인트 데이터를 초기화 완료했습니다.");
    }

    @Scheduled(cron = "0 30 0 * * 1")
    public void calculateAllUserRank() {
        log.info("랭킹 산정을 시작합니다.");
        LocalDateTime previousWeekDate = LocalDateTime.now().minusDays(1);
        int row = rankDao.calculatePreviousRank(previousWeekDate);
        log.info(row + "명의 랭킹이 산정되었습니다.");
    }

    private Rank createRankByUser(User user) {

        LocalDateTime startDate = LocalDateTime.now().plusDays(1).with(LocalTime.MIN);

        return Rank.builder()
                .user(user)
                .weekPoint(0)
                .startDate(startDate)
                .endDate(startDate.plusDays(7))
                .build();
    }

    public ReadCurrentRankResponseDTO readCurrentRankPoint(long userId) {
        User user = findUser(userId);
        LocalDateTime now = LocalDateTime.now();

        Rank rank = rankRepository.findByUserAndStartDateLessThanEqualAndEndDateGreaterThanEqual(user, now, now)
                .orElseThrow(()-> new RestApiException(UserErrorCode.NOT_EXIST_RANK_EXCEPTION));

        return ReadCurrentRankResponseDTO.toDTO(user, rank);
    }

    public ReadRankResponseDTO readPreviousWeekRank(long userId) {
        User user = findUser(userId);

        LocalDateTime previousWeek = LocalDateTime.now().minusWeeks(1);

        Rank rank = rankRepository.findByUserAndStartDateLessThanEqualAndEndDateGreaterThanEqual(user, previousWeek, previousWeek)
                .orElseThrow(()-> new RestApiException(UserErrorCode.NOT_EXIST_RANK_EXCEPTION));

        return ReadRankResponseDTO.toDTO(rank, user);
    }

    public Page<ReadRankResponseDTO> readAllPreviousWeekRank(Pageable pageable) {

        LocalDateTime previousWeek = LocalDateTime.now().minusWeeks(1);

        Page<Rank> rankPage = rankRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(pageable, previousWeek, previousWeek);

        return rankPage.map(rank -> ReadRankResponseDTO.toDTO(rank , rank.getUser()));
    }

    public User findUser(long userId) {
        return userRepository.findByIdAndDeleted(userId, false)
                .orElseThrow(()-> new RestApiException(UserErrorCode.NOT_EXIST_USER_EXCEPTION));
    }
}
