package study.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.apiPayload.ApiResponse;
import study.converter.TempConverter;
import study.service.TempService.TempQueryService;
import study.web.dto.TempResponse;

//@RestController와 @ReqiredArgsConstructor가 둘이 어떤 시너지를 내는지, 어떤 역할을 하는지 이해하는 것이 매우 중요
//의존성 주입.
@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor //의존성 주입
public class TempController {
    private final TempQueryService tempQueryService;

    @GetMapping("/test")
    public ApiResponse<TempResponse.TempTestDTO> testPage() {
        return ApiResponse.onSuccess(TempConverter.toTempTestDTO());
    }

    @GetMapping("/v1/exception")
    public ApiResponse<TempResponse.TempExceptionDTO> exceptionPageV1(@RequestParam Integer flag) {
        tempQueryService.checkFlag(flag);
        TempResponse.TempExceptionDTO tempExceptionDTO = TempConverter.toTempExceptionDTO(flag);
        System.out.println("tempExceptionDTO.toString() = " + tempExceptionDTO.toString());
        return ApiResponse.onSuccess(tempExceptionDTO);
    }

    @GetMapping("/v2/exception")
    public ResponseEntity<TempResponse.TempExceptionDTO> exceptionPageV2(@RequestParam Integer flag) {
        tempQueryService.checkFlag(flag);
        TempResponse.TempExceptionDTO tempExceptionDTO = TempConverter.toTempExceptionDTO(flag);
        return ResponseEntity.ok(tempExceptionDTO);//200 success 201 created
    }

}
