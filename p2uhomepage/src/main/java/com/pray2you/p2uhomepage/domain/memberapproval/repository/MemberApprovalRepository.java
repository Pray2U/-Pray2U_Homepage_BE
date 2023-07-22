package com.pray2you.p2uhomepage.domain.memberapproval.repository;

import com.pray2you.p2uhomepage.domain.memberapproval.entity.MemberApproval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberApprovalRepository extends JpaRepository<MemberApproval, Long> {

    boolean existsByGithubId(String githubId);
    Optional<MemberApproval> findByGithubId(String githubId);
}
