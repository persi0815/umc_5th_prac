package study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import study.apiPayload.code.status.ErrorStatus;
import study.apiPayload.exception.handler.StoreHandler;
import study.domain.Member;
import study.domain.Review;
import study.domain.Store;
import study.repository.MemberRepository;
import study.repository.ReviewRepository;
import study.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class StoreQueryServiceImpl implements StoreQueryService{

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Override
    public Store findById(Long id) {
        return storeRepository.findById(id).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
    }

    @Override
    public boolean existsById(Long id) {
        return storeRepository.existsById(id);
    }


    @Override
    public Page<Review> getReviewList(Long storeId, Integer page, Long memberId) {
        Store store = storeRepository.findById(storeId).get();

        if (memberId != null) {
            Member member = memberRepository.findById(memberId).get();
            return reviewRepository.findAllByStoreAndMember(store, member, PageRequest.of(page, 10));
        }

        return reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
    }
}