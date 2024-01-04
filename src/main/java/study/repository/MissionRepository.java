package study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import study.domain.Mission;
import study.domain.enums.MissionStatus;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> findAllByStoreId(Long storeId);

    @Query("select m from Mission as m join fetch m.memberMissionList as l where l.member.id=:memberId and l.status=:status")
    List<Mission> findAllByMemberIdAndStatus(Long memberId, MissionStatus status);
}
/*
    @Query("SELECT m FROM Mission m JOIN m.store s WHERE s.name = :storeName")
    Page<Mission> findAllByStoreName(String storeName, Pageable pageable);
//결과를 객체에 담어서 줌
    @Query("SELECT m FROM Mission m JOIN m.member s WHERE s.name = :storeName AND  status == "미완료" ) //member-id에 맞게 바뀜
    Page<Mission> findAllByMemberId(String storeName, Pageable pageable);
}
 */
