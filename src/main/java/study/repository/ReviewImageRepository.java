package study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.domain.FoodCategory;
import study.domain.ReviewImage;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
}
