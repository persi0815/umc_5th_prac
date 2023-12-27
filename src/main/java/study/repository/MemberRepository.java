package study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.domain.Member;

//Spring Data JPA를 사용할 것이기에 인터페이스로 만들어줌
public interface MemberRepository extends JpaRepository<Member, Long> {

}
