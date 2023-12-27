package study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.domain.FoodCategory;

public interface ReviewImageRepository extends JpaRepository<FoodCategory, Long> {
}
