package study.domain;
import lombok.*;
import study.domain.common.BaseTime;
import study.domain.mapping.MemberAgree;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Terms extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(nullable = false, length = 20)
    @Column(columnDefinition = "varchar(20)")
    private String title;

    //원래 text type
    @Lob
    private String body;

    private Boolean optional;

    //양방향 매핑
    @OneToMany(mappedBy = "terms", cascade = CascadeType.ALL)
    private List<MemberAgree> memberAgreeList = new ArrayList<>();
}