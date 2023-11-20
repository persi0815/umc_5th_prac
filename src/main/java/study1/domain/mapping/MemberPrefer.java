package study1.domain.mapping;

import lombok.*;
import javax.persistence.*;

import study1.domain.FoodCategory;
import study1.domain.Member;
import study1.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberPrefer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //연관 관계 주인. 실제 데이터베이스에서 외래키를 가지는 엔티티(테이블)
    //단방향 매핑. 연관 관계 주인에게만 연관 관계를 주입하는 것
    //양방향 매핑은 연관 관계 주인이 아닌 엔티티에게도 연관 관계를 주입하는 것

    //아래는 단방향 매핑

    @ManyToOne(fetch = FetchType.LAZY) //지연로딩 설정
    //LAZY: member가 필요한 순간에만 참조하겠다. 순환 참조 개선
    @JoinColumn(name = "member_id")
    //이름은 이렇게 지어줄 수 있는데, 이걸 안쓰면 기본적으로는 ~게 온다.
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private FoodCategory foodCategory;
}