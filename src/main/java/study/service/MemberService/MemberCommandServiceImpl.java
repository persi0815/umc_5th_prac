package study.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.apiPayload.code.status.ErrorStatus;
import study.apiPayload.exception.handler.FoodCategoryHandler;
import study.converter.MemberConverter;
import study.converter.MemberPreferConverter;
import study.domain.FoodCategory;
import study.domain.Member;
import study.domain.mapping.MemberPrefer;
import study.repository.FoodCategoryRepository;
import study.repository.MemberPreferRepository;
import study.repository.MemberRepository;
import study.web.dto.MemberRequestDto;
import org.springframework.transaction.annotation.Transactional;
//import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
//@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final MemberPreferRepository memberPreferRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDto.JoinDto request) {
        Member member = MemberConverter.toMember(request);
        setMemberPreferList(request, member);

        return memberRepository.save(member);
    }

    private void setMemberPreferList(MemberRequestDto.JoinDto request, Member member) {
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND))).toList();
        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(member, foodCategoryList);

        memberPreferRepository.saveAll(memberPreferList);
    }
}