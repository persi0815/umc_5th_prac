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
public class Store extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(50)")
    private String name;

    @Column(columnDefinition = "varchar(50)")
    private String address;

    private Float score;

    //양방향
    //@OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    //private List<Review> reviewList = new ArrayList<>();

    //단방향
    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "region_id")
    private Region region;
}