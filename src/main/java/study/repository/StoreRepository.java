package study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.domain.Store;
import study.domain.mapping.MemberPrefer;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
