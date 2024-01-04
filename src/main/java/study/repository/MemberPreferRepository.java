package study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.domain.mapping.MemberMission;
import study.domain.mapping.MemberPrefer;

public interface MemberPreferRepository extends JpaRepository<MemberPrefer, Long> {
}
