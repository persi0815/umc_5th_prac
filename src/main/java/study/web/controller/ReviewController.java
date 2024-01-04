package study.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import study.apiPayload.ApiResponse;
import study.converter.MissionConverter;
import study.converter.ReviewConverter;
import study.domain.Mission;
import study.domain.Review;
import study.service.ReviewService.ReviewCommandService;
import study.validation.annotation.ExistMember;
import study.validation.annotation.ExistStore;
import study.web.dto.MissionResponseDto;
import study.web.dto.ReviewResponseDto;
import study.web.dto.ReviewRequestDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("reviews")
public class ReviewController {

    private final ReviewCommandService reviewService;

    @GetMapping() //"/member-reviews"
    public ApiResponse<ReviewResponseDto.ReviewListDto> getMemberReviewList(
            @ExistMember @RequestParam(name = "memberId", required = false) Long memberId) {
        List<Review> reviewList = reviewService.findAllByMemberId(memberId);
        ReviewResponseDto.ReviewListDto reviewListDto = ReviewConverter.toReviewListDto(reviewList);

        return ApiResponse.onSuccess(reviewListDto);
    }
}
/*
public class ReviewController {
    @GetMapping("my-reviews")
    public ApiResponse<Page<ReviewResponseDto.ReviewGetDto>> readByMemberId(@RequestParam("member-id") Long memberId,
                                                                            @RequestParam(defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        Page<ReviewResponseDto.ReviewGetDto> pagedDto = ReviewService.readByMemberId(memberId, pageable);
        return ApiResponse.onSuccess(ReviewConverter.toMissionGetDto(pagedDto));
    }
}

 */
