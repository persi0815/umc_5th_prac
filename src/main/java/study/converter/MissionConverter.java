package study.converter;

import study.domain.Mission;
import study.web.dto.MissionResponseDto;

import java.util.List;

public class MissionConverter {
    public static MissionResponseDto.MissionListDto toMissionListDto(List<Mission> missionList) {
        List<MissionResponseDto.MissionDto> missionDtoList = missionList.stream()
                .map(mission -> MissionResponseDto.MissionDto.builder()
                        .reward(mission.getReward())
                        .storeName(mission.getStore().getName())
                        .missionSpec(mission.getMissionSpec())
                        .build()).toList();

        return MissionResponseDto.MissionListDto.builder()
                .missionDtoList(missionDtoList)
                .build();
    }
}
