package study.domain;
import lombok.*;
import study.domain.common.BaseTime;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Region extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(nullable = false, length = 20)
    @Column(columnDefinition = "varchar(20)")
    private String name;

    //양방향
    //@OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    //private List<Store> storeList = new ArrayList<>();
    //region에서는 store에 접근할 필요x
}