package study.service.ReviewService;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.domain.Review;
import study.domain.ReviewImage;
import study.repository.ReviewImageRepository;
import study.converter.ReviewConverter;
import study.converter.ReviewImageConverter;
import study.service.ReviewService.ReviewCommandService;
import study.web.dto.ReviewRequestDTO;
import study.web.dto.ReviewRequestDTO.WriteDto;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewImageRepository reviewImageRepository;

    @Override
    public Review writeReview(ReviewRequestDTO.WriteDto writeDto, Long storeId) {
        Review review = ReviewConverter.toReview(writeDto, storeId);
        setReviewImageList(writeDto, review);

        return review;
    }

    private void setReviewImageList(WriteDto writeDto, Review review) {
        List<ReviewImage> reviewImageList = ReviewImageConverter.toReviewImageList(review, writeDto.getImage());

        reviewImageRepository.saveAll(reviewImageList);
    }
}