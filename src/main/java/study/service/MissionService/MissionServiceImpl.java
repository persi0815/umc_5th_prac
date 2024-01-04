package study.service.MissionService;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.domain.Mission;
import study.domain.enums.MissionStatus;
import study.domain.mapping.MemberMission;
import study.repository.MemberMissionRepository;
import study.repository.MissionRepository;

//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public List<Mission> findAllByStoreId(Long storeId) {
        return missionRepository.findAllByStoreId(storeId);
    }

    @Override
    public List<Mission> findAllByMemberIdAndStatus(Long memberId, MissionStatus status) {
        return missionRepository.findAllByMemberIdAndStatus(memberId, status);
    }

    @Override
    public void changeStatus(Long missionId, Long memberId) {
        MemberMission memberMission = memberMissionRepository.findByMissionIdAndMemberId(missionId, memberId);
        memberMission = memberMission.toBuilder() //오류나서 MemberMission의 @Builder 수정
                .status(MissionStatus.COMPLETE)
                .build();
        memberMissionRepository.save(memberMission);
    }

}
/* 광현
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;

    @Override
    public Page<MissonResponseDto.MissonGetDto> readByStoreName(String storeName, Pageable pageable) {
        Page<Mission> pagedMission = missionRepository.findAllByStoreName(storeName, pageable);
        Page<MissonResponseDto.MissonGetDto> pagedDto = pagedMission
                .map(mission -> MissionConverter.from(mission));
        return pagedDto;
    }
}
 */

