package study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    MemberMission findByMissionIdAndMemberId(Long missionId, Long memberId);
}
