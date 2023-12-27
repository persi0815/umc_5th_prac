package study.converter;

import study.domain.Review;
import study.service.MemberService.MemberQueryService;
import study.service.StoreService.StoreQueryService;
import study.web.dto.ReviewRequestDTO;
import study.web.dto.ReviewResponseDTO;
import study.web.dto.ReviewResponseDTO.WriteResultDto;

public class ReviewConverter {

    private static MemberQueryService memberQueryService;
    private static StoreQueryService storeQueryService;

    public static Review toReview(ReviewRequestDTO.WriteDto writeDto, Long storeId) {

        return Review.builder()
                .body(writeDto.getBody())
                .score(writeDto.getScore())
                .member(memberQueryService.findById(writeDto.getMemberId()))
                .store(storeQueryService.findById(storeId))
                .build();
    }

    public static ReviewResponseDTO.WriteResultDto toWriteResultDto(Review review) {
        return WriteResultDto.builder()
                .id(review.getId())
                .build();
    }
}