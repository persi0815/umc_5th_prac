package study.service.StoreService;

import org.springframework.data.domain.Page;
import study.domain.Review;
import study.domain.Store;

import java.util.Optional;

public interface StoreQueryService {
    //Optional<Store> findStore(Long id);

    Store findById(Long id); //StoreId
    boolean existsById(Long id);
    Page<Review> getReviewList(Long storeId, Integer page, Long memberId);
}
