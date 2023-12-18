package com.pray2you.p2uhomepage.domain.point.event;

import com.pray2you.p2uhomepage.domain.point.entity.DetailPoint;
import com.pray2you.p2uhomepage.domain.point.entity.Rank;
import com.pray2you.p2uhomepage.domain.point.entity.TotalPoint;
import com.pray2you.p2uhomepage.domain.point.repository.DetailPointRepository;
import com.pray2you.p2uhomepage.domain.point.repository.RankRepository;
import com.pray2you.p2uhomepage.domain.point.repository.TotalPointRepository;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import com.pray2you.p2uhomepage.global.exception.RestApiException;
import com.pray2you.p2uhomepage.global.exception.errorcode.UserErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.io.BufferedReader;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class PointEventListener {

    private final RankRepository rankRepository;
    private final TotalPointRepository totalPointRepository;
    private final DetailPointRepository detailPointRepository;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addPoint(PointAddEvent event) {

        log.info("포인트 이벤트 실행");

        addTotalPoint(event);
        createDetailPoint(event);
        addRankPoint(event);
    }

    @EventListener
    public void orderItem(OrderItemEvent event) {
        log.info("주문 포인트 이벤트 실행");

        addTotalPoint(event);
        createDetailPoint(event);
    }

    private void addRankPoint(PointEvent event){
        User user = event.getUser();

        Rank rank = rankRepository.findByUserAndStartDateLessThanEqualAndEndDateGreaterThanEqual(user, LocalDateTime.now(), LocalDateTime.now())
                .orElse(null);

        if(rank == null) {
            log.warn(user.getUsername() + "해당 랭크 정보가 없습니다.");
            return;
        }

        log.info(rank.getUser().getUsername() + " 현재 랭크 포인트 : " + rank.getWeekPoint());

        rank.updatePoint(event.getPoint());

        Rank savedRank = rankRepository.save(rank);

        log.info(savedRank.getUser().getUsername() + " 증가된 랭크 포인트 : " + savedRank.getWeekPoint());
    }

    private void addTotalPoint(PointEvent event) {

        User user = event.getUser();

        TotalPoint totalPoint = totalPointRepository.findByUser(user)
                .orElseThrow(() -> new RestApiException(UserErrorCode.NOT_EXIST_TOTAL_POINT_EXCEPTION));

        log.info(totalPoint.getUser().getUsername() + " 현재 전체 포인트 : " + totalPoint.getCurrentPoint());
        totalPoint.addPoint(event.getPoint());

        TotalPoint savedTotalPoint = totalPointRepository.save(totalPoint);

        log.info(savedTotalPoint.getUser().getUsername() + " 증가된 전체 포인트 : " + savedTotalPoint.getCurrentPoint());
    }

    private void createDetailPoint(PointEvent event) {

        DetailPoint detailPoint = event.toDetailPoint();
        DetailPoint savedDetailPoint = detailPointRepository.save(detailPoint);

        log.info(savedDetailPoint.getUser().getUsername() + " 컨텐츠 : " + savedDetailPoint.getContent());
        log.info(savedDetailPoint.getUser().getUsername() + " 증가된 포인트 : " + savedDetailPoint.getPoint());
    }
}
