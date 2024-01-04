package study.domain;
import lombok.*;
import study.domain.common.BaseTime;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //원래 text type
    private String title;
    @Lob
    private String body; //워크북에는 title이라 적혀있었음

    private Float score;

    //단방향
    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "store_id")
    private Store store;

    //양방향
    //@OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    //private List<ReviewImage> reviewImageList = new ArrayList<>();
}