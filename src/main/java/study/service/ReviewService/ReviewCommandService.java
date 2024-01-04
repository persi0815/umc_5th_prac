package study.service.ReviewService;

import study.domain.Mission;
import study.domain.Review;
import study.web.dto.ReviewRequestDto;

import java.util.List;

public interface ReviewCommandService {
    List<Review> findAllByMemberId(Long storeId);

    Review writeReview(ReviewRequestDto.WriteDto writeDto, Long storeId);


}