package study.web.controller;

import study.converter.ReviewConverter;
import study.domain.Review;
import study.apiPayload.ApiResponse;
import study.service.ReviewService.ReviewCommandService;
import study.web.dto.ReviewRequestDTO;
import study.web.dto.ReviewResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping("/{storeId}/review/w")
    public ApiResponse<ReviewResponseDTO.WriteResultDto> write(@RequestBody ReviewRequestDTO.WriteDto writeDto, @PathVariable Long storeId) {
        Review review = reviewCommandService.writeReview(writeDto, storeId);
        return ApiResponse.onSuccess(ReviewConverter.toWriteResultDto(review));
    }
}