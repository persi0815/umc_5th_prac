package study.service.ReviewService;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.apiPayload.code.status.ErrorStatus;
import study.apiPayload.exception.handler.MemberHandler;
import study.apiPayload.exception.handler.StoreHandler;
import study.domain.*;
import study.repository.MemberRepository;
import study.repository.ReviewImageRepository;
import study.converter.ReviewConverter;
import study.converter.ReviewImageConverter;
import study.repository.StoreRepository;
import study.web.dto.ReviewRequestDto;
import study.web.dto.ReviewRequestDto.WriteDto;
import study.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ReviewImageRepository reviewImageRepository;

    @Override
    public Review writeReview(ReviewRequestDto.WriteDto writeDto, Long storeId) {
        Member member = memberRepository.findById(writeDto.getMemberId()).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
        Review review = ReviewConverter.toReview(writeDto, member, store);
        setReviewImageList(writeDto, review);

        return review;
    }

    private void setReviewImageList(WriteDto writeDto, Review review) {
        List<ReviewImage> reviewImageList = ReviewImageConverter.toReviewImageList(review, writeDto.getImage());

        reviewImageRepository.saveAll(reviewImageList);
    }

    @Override
    public List<Review> findAllByMemberId(Long memberId) {
        return reviewRepository.findAllByMemberId(memberId);
    }


}
