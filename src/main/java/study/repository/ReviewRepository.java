package study.repository;

import study.domain.Member;
import study.domain.Mission;
import study.domain.Review;
import study.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
    Page<Review> findAllByStoreAndMember(Store store, Member member, PageRequest pageRequest);

    List<Review> findAllByMemberId(Long memberId);

}
