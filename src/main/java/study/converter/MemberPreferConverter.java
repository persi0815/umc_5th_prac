package study.converter;

import study.domain.FoodCategory;
import study.domain.Member;
import study.domain.mapping.MemberPrefer;
import java.util.List;

public class MemberPreferConverter {

    public static List<MemberPrefer> toMemberPreferList(Member member, List<FoodCategory> foodCategoryList) {
        return foodCategoryList.stream()
                .map(foodCategory -> MemberPrefer.builder()
                        .member(member)
                        .foodCategory(foodCategory)
                        .build()
                ).toList();
    }
}
