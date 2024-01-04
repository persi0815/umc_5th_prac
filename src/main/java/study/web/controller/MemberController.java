package study.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.apiPayload.ApiResponse;
import study.converter.MemberConverter;
import study.domain.Member;
import study.web.dto.MemberRequestDto;
import study.web.dto.MemberResponseDto;
import study.service.MemberService.MemberCommandService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("members")
public class MemberController {

    private final MemberCommandService memberCommandService;

    @PostMapping
    public ApiResponse<MemberResponseDto.JoinResultDto> join(@RequestBody @Valid MemberRequestDto.JoinDto request) {
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }
}
/*
public class MemberController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/register")
    public ApiResponse<MemberResponseDto.JoinResultDto> join(@RequestBody @Valid MemberRequestDto.JoinDto request) {
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @GetMapping
    public String task(){
        return "hello";
    }
}

 */
    //@Valid 어노테이션이 존재하므로 @ExistCategories에서 발생한 예외가 바로 전달이 되지 않고,
    //@Valid 어노테이션이 MethodArgumentNotValidException를 발생시킴
