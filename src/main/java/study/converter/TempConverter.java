package study.converter;

import study.web.dto.TempResponse;
//컨버터 함수 이름은 to<만들려는 대상>
public class TempConverter {

    public static TempResponse.TempTestDTO toTempTestDTO() {
        return TempResponse.TempTestDTO.builder()
                .testString("temp response 성공")
                .build();
    }

    public static TempResponse.TempExceptionDTO toTempExceptionDTO(Integer flag) {
        return TempResponse.TempExceptionDTO.builder()
                .flag(flag)
                .build();
    }
}
