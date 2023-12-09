package com.pray2you.p2uhomepage.domain.point.service;

import com.pray2you.p2uhomepage.domain.point.entity.DetailPoint;
import com.pray2you.p2uhomepage.domain.point.enumeration.PointContent;
import com.pray2you.p2uhomepage.domain.point.event.PointAddEvent;
import com.pray2you.p2uhomepage.domain.point.repository.DetailPointRepository;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import com.pray2you.p2uhomepage.domain.user.repository.UserRepository;
import com.pray2you.p2uhomepage.global.exception.RestApiException;
import com.pray2you.p2uhomepage.global.exception.errorcode.UserErrorCode;
import com.pray2you.p2uhomepage.global.utils.RandomPointUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommitPointService {

    private GitHub github;
    @Value("${spring.api.github}")
    private String token;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final DetailPointRepository detailPointRepository;

    private void connectToGithub(String token) throws IOException {
        github = new GitHubBuilder().withOAuthToken(token).build();
        github.checkApiUrlValidity();
    }

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void createCommitPointService() throws IOException{

        log.info("커밋 포인트 정산");
        connectToGithub(token);
        List<User> users = userRepository.findAllByDeleted(false);

        users.forEach(this::createCommitPoint);
        log.info("커밋 포인트 정산 완료");
    }

    private void createCommitPoint(User user) {
        if (searchCommits(user).getTotalCount() > 0) {

            LocalDateTime thisMonday = LocalDateTime.now().with(DayOfWeek.MONDAY).with(LocalTime.MIN);
            List<DetailPoint> commitPoints = detailPointRepository.findAllByUserAndContentAndCreateDateBetween(user, PointContent.COMMIT, thisMonday, thisMonday.plusDays(7));

            if (commitPoints.size() > 2) {
                log.warn("커밋 포인트 횟수 제한으로 인해 포인트가 증가하지 않습니다.");
                throw new RestApiException(UserErrorCode.COMMIT_RESTRICT_EXCEPTION);
            }
            saveCommitPoint(user);
        }
    }

    private PagedSearchIterable<GHCommit> searchCommits(User user) {
        GHCommitSearchBuilder builder = github.searchCommits()
                .author(user.getGithubId())
                .authorDate(LocalDate.now().minusDays(1).toString());

        builder.list();

        return builder.list();
    }

    private void saveCommitPoint(User user) {
        int randomPoint = RandomPointUtil.getRandomPoint(PointContent.COMMIT);
        applicationEventPublisher.publishEvent(new PointAddEvent(PointContent.COMMIT, randomPoint, user));
    }
}
