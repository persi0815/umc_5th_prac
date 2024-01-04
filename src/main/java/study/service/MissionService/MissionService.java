package study.service.MissionService;

import org.springframework.data.domain.Pageable;
import study.domain.Mission;
import study.domain.enums.MissionStatus;

import java.util.List;

public interface MissionService {
    List<Mission> findAllByStoreId(Long storeId);

    List<Mission> findAllByMemberIdAndStatus(Long memberId, MissionStatus status);

    void changeStatus(Long missionId, Long memberId);

    //Page<MissonResponseDto.MissonGetDto> readByStoreName(String storeName, Pageable pageable);

}
