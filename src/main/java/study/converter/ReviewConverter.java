package study.converter;

import study.domain.Member;
import study.domain.Mission;
import study.domain.Review;
import study.domain.Store;
import study.service.MemberService.MemberQueryService;
import study.service.StoreService.StoreQueryService;
import study.web.dto.MissionResponseDto;
import study.web.dto.ReviewRequestDto;
import study.web.dto.ReviewResponseDto;
import study.web.dto.ReviewResponseDto.WriteResultDto;

import java.util.List;

public class ReviewConverter {

    public static ReviewResponseDto.ReviewListDto toReviewListDto(List<Review> reviewList) {
        List<ReviewResponseDto.ReviewDto> reviewDtoList = reviewList.stream()
                .map(review -> ReviewResponseDto.ReviewDto.builder()
                        .title(review.getTitle())
                        .memberName(review.getMember().getName())
                        .body(review.getBody())
                        .build()).toList();

        return ReviewResponseDto.ReviewListDto.builder()
                .reviewDtoList(reviewDtoList)
                .build();
    }

    public static Review toReview(ReviewRequestDto.WriteDto writeDto, Member member, Store store) {

        return Review.builder()
                .body(writeDto.getBody())
                .score(writeDto.getScore())
                .member(member)
                .store(store)
                .build();
    }

    public static ReviewResponseDto.WriteResultDto toWriteResultDto(Review review) {
        return WriteResultDto.builder()
                .id(review.getId())
                .build();
    }
}
