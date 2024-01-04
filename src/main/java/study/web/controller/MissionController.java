package study.web.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import study.apiPayload.ApiResponse;
import study.converter.MissionConverter;
import study.domain.Mission;
import study.domain.enums.MissionStatus;
import study.service.MissionService.MissionService;
import study.validation.annotation.ExistStore;
import study.web.dto.MissionResponseDto;
import study.web.dto.MissionResponseDto.MissionListDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("missions")
public class MissionController {

    private final MissionService missionService;

    @GetMapping() //"/store-missions"
    public ApiResponse<MissionResponseDto.MissionListDto> getStoreMissionList(
            @ExistStore @RequestParam(name = "storeId", required = false) Long storeId) {
        List<Mission> missionList = missionService.findAllByStoreId(storeId);
        MissionResponseDto.MissionListDto missionListDto = MissionConverter.toMissionListDto(missionList);

        return ApiResponse.onSuccess(missionListDto);
    }

    @GetMapping("/my-missions-ing")
    public ApiResponse<MissionResponseDto.MissionListDto> getMyMissionList(
            @RequestParam(name = "memberId") Long memberId,
            @RequestParam(name = "status", required = false) String status
    ) {
        List<Mission> missionList = missionService.findAllByMemberIdAndStatus(memberId, MissionStatus.valueOf(status));
        MissionListDto missionListDto = MissionConverter.toMissionListDto(missionList);

        return ApiResponse.onSuccess(missionListDto);
    }

    @PatchMapping("/my-missions-ing")
    public ApiResponse<Boolean> changeMissionStatus(
            @RequestParam(name = "missionId", required = false) Long missionId,
            @RequestParam(name = "memberId") Long memberId
    ) {
        missionService.changeStatus(missionId, memberId);

        return ApiResponse.onSuccess(true);
    }

}
/*시도
public class MissionController {
    private final MissionService missionService;
    @GetMapping("/store-missions")
    public ApiResponse<Page<MissonResponseDto.MissonGetDto>> readByStoreName(@RequestParam("store-id") Long storeName,
                                                            @RequestParam(defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        Page<MissonResponseDto.MissonGetDto> pagedDto = missionService.readByStoreName(storeName, pageable);
        return ApiResponse.onSuccess(MissionConverter.toMissionGetDto(pagedDto));
    }
    @GetMapping("/my-missions-ing")
    public ApiResponse<Page<MissonResponseDto.MissonGetDto>> readByMemberId(@RequestParam("member-id") Long memberId,
                                                                             @RequestParam(defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        Page<MissonResponseDto.MissonGetDto> pagedDto = missionService.readByMemberId(memberId, pageable);
        return ApiResponse.onSuccess(MissionConverter.toMissionGetDto(pagedDto));
    }
}

 */
