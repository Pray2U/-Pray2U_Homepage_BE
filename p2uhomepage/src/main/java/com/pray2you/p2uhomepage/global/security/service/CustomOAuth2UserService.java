package com.pray2you.p2uhomepage.global.security.service;

import com.pray2you.p2uhomepage.domain.memberapproval.repository.MemberApprovalRepository;
import com.pray2you.p2uhomepage.domain.memberapproval.enumeration.ApprovalStatus;
import com.pray2you.p2uhomepage.domain.user.enumeration.Role;
import com.pray2you.p2uhomepage.domain.user.entity.User;
import com.pray2you.p2uhomepage.domain.user.repository.UserRepository;
import com.pray2you.p2uhomepage.global.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Slf4j
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final MemberApprovalRepository memberApprovalRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(oAuth2UserRequest);

        if(!checkMemberApproval(oAuth2User.getAttribute("login"))){
            throw new OAuth2AuthenticationException("이미 가입되었거나, 미승인 유저입니다.");
        }

        User user = saveOrUpdate(oAuth2User);
        log.info(user.getGithubId());

        return CustomUserDetails.create(user, oAuth2User.getAttributes());
    }

    private boolean checkMemberApproval(String githubId){
        return memberApprovalRepository.existsByGithubIdAndStatus(githubId,  ApprovalStatus.APPROVED);
    }

    private User saveOrUpdate(OAuth2User oAuth2User){
        User oAuthUser = User.builder()
                .githubId(oAuth2User.getAttribute("login"))
                .username(oAuth2User.getAttribute("name"))
                .profileImgUrl(oAuth2User.getAttribute("avatar_url"))
                .phoneNumber("010-0000-0000")
                .email("test@test.com")
                .role(Role.ROLE_GUEST)
                .build();

        User user = userRepository.findByGithubIdAndDeleted(oAuthUser.getGithubId(), false)
                .orElse(oAuthUser);

        return userRepository.save(user);
    }

}
