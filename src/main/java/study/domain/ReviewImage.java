package study.domain;

import lombok.*;
import study.domain.common.BaseTime;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReviewImage extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String imageUrl; //원래 text type

    //단방향
    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "review_id")
    private Review review;

}