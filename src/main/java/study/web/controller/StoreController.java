package study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import study.converter.ReviewConverter;
import study.converter.StoreConverter;
import study.domain.Review;
import study.apiPayload.ApiResponse;
import study.service.ReviewService.ReviewCommandService;
import study.service.StoreService.StoreQueryService;
import study.validation.annotation.ExistStore;
import study.web.dto.ReviewRequestDto;
import study.web.dto.ReviewResponseDto;
import lombok.RequiredArgsConstructor;
import study.web.dto.StoreResponseDto;

//import study.service.StoreService.StoreQueryService;
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final ReviewCommandService reviewCommandService;
    private final StoreQueryService storeQueryService;

    @PostMapping("/{storeId}/review/w")
    public ApiResponse<ReviewResponseDto.WriteResultDto> write(@RequestBody ReviewRequestDto.WriteDto writeDto, @PathVariable Long storeId) {
        Review review = reviewCommandService.writeReview(writeDto, storeId);
        return ApiResponse.onSuccess(ReviewConverter.toWriteResultDto(review));
    }

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
            @Parameter(name = "memberId", description = "멤버의 id입니다.")
    })
    public ApiResponse<ReviewResponseDto.ReviewPreViewListDto> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                             @RequestParam(name = "page") Integer page,
                                                                             @RequestParam(name = "memberId", required = false) Long memberId) {
        Page<Review> reviewList = storeQueryService.getReviewList(storeId, page, memberId);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDto(reviewList));
    }
}
