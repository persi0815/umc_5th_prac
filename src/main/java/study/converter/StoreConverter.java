package study.converter;

import study.domain.Review;
import study.web.dto.ReviewResponseDto;
import study.web.dto.ReviewRequestDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;

public class StoreConverter {

    public static Review toReview(ReviewRequestDto.ReviewDTO request){
        return Review.builder()
                .title(request.getTitle())
                .score(request.getScore())
                .body(request.getBody())
                .build();
    }

    public static ReviewResponseDto.CreateReviewResultDto toCreateReviewResultDTO(Review review){
        return ReviewResponseDto.CreateReviewResultDto.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static ReviewResponseDto.ReviewPreViewDto reviewPreViewDto(Review review){
        return ReviewResponseDto.ReviewPreViewDto.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    public static ReviewResponseDto.ReviewPreViewListDto reviewPreViewListDto(Page<Review> reviewList){

        List<ReviewResponseDto.ReviewPreViewDto> reviewPreViewDtoList = reviewList.stream()
                .map(StoreConverter::reviewPreViewDto).collect(Collectors.toList());

        return ReviewResponseDto.ReviewPreViewListDto.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDtoList.size())
                .reviewList(reviewPreViewDtoList)
                .build();
    }
}