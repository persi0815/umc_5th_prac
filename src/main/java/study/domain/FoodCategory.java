package study.domain;
import lombok.*;
import study.domain.common.BaseTime;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FoodCategory extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //음식 카테고리는 보통 수정/삭제를 잘 하지 않으므로 양방향 매핑을 하지 않음
}