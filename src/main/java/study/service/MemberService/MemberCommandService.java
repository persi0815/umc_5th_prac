package study.service.MemberService;

import study.domain.Member;
import study.web.dto.MemberRequestDto;

public interface MemberCommandService {
    Member joinMember(MemberRequestDto.JoinDto request);
}
