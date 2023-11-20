package study1.domain;

import lombok.*;
import study1.domain.common.BaseEntity;
import study1.domain.enums.Gender;
import study1.domain.enums.MemberStatus;
import study1.domain.enums.SocialType;
import study1.domain.mapping.MemberAgree;
import study1.domain.mapping.MemberMission;
import study1.domain.mapping.MemberPrefer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
//해당 클래스가 JPA의 엔티티임을 명시
@Getter
//lombok에서 제공해주는 것으로, getter를 만들어주는 어노테이션
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
//위 세 개의 어노테이션은 자바의 디자인 패턴 중 하나인 빌더 패턴을 사용하기 위함
//빌더 패턴을 사용하면 생성자를 사용하는 것보다 더욱 편리하게 코딩이 가능

public class Member extends BaseEntity{
//모든 엔티티 클래스마다 BaseEntity 클래스를 상속받으면 됨

    @Id //기본 키
    //기본 키를 만드는 방법에는 여러가지가 있지만, 가장 편리한 아래의 방법 이용
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //JPA가 통신을 하는 DBMS의 방식을 따른다는 뜻
    //새로운 레코드 만들어질떄 id가 만들어지는 기준을 db에 위임하겠다.
    //db에서는 보통 ai(auto increment사용) - MYSQL
    //명시안해주면 id 하나하나 만들어야 함.

    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 40)
    private String address;

    @Column(nullable = false, length = 40)
    private String specAddress;

    //gender, status, social_type은 정해진 값들 중에 특정 값이 저장이 됨
    //-> enum을 사용하는 것이 좋음
    @Enumerated(EnumType.STRING) //반드시 EnumType을 STRING!!
    //기본 값인 ORDINAL을 사용하면 데이터베이스에 enum의 순서가 저장이 되는데,
    //만약 Springboot에서 enum의 순서를 바꾸게 될 경우 에러 생김!!
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private SocialType socialType; //어떤 플랫폼 통해 로그인하는지

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    //@Column(nullable = false, length = 40) 처럼도 사용능
    private MemberStatus status;

    private LocalDate inactiveDate;

    //어떤 값이 저장될 지 모르기에 그냥 String 형태
    @Column(nullable = false, length = 50)
    private String email;

    private Integer point;

    //여기까지 필드 정의

    //여기부터 연관관계 매핑


    //양방향 매핑 -> 연관 관계 편의 메서드가 필요!!
    //장점: 객체 그래프 탐색으로 인한 이점이 있음 & cascade 설정가능
    //JPA의 cascade: 참조의 대상이 되는 엔티티에 설정을 해서
    //               참조 대상인 테이블의 칼럼이 삭제 될 때 같이 삭제되거나 변경이 될 때 같이 변경이 되는 기능
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    //보통 CascadeType.ALL을 사용
    //-> 상위 엔터티에서 하위 엔터티로 “모든 작업”을 전파
    //Member의 변화에 따라 emberAgree 엔티티가 영향을 받음
    private List<MemberAgree> memberAgreeList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    //@OneToMany 어노테이션으로 1에 해당하는 엔티티가 N에 해당하는 엔티티와 관계가 있음을 명시
    //N에 해당하는 엔티티에서 ManyToOne이 설정 된 멤버변수를 mappedBy함
    //mappedBy를 왜 사용하나?
    // -> 연관관계의 주인이 아니고, 어노테이션이 붙은 필드에서 조회는 할 수 있지만, db상에 직접적 정보가 없음

    //=> 멤버가 삭제 될 때, 멤버를 참조하는 나머지 데이터도 같이 삭제가 됨
    private List<MemberPrefer> memberPreferList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    //일대다에서는 List 사용
    private List<MemberMission> memberMissionList = new ArrayList<>();

}