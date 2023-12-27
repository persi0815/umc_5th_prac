package study.service.ReviewService;

import study.domain.Review;
import study.web.dto.ReviewRequestDTO;

public interface ReviewCommandService {
    Review writeReview(ReviewRequestDTO.WriteDto writeDto);

    Review writeReview(ReviewRequestDTO.WriteDto writeDto, Long storeId);
}
