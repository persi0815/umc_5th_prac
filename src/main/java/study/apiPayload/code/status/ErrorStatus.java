package study.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import study.apiPayload.code.BaseErrorCode;
import study.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    //MEMBER ERROR
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수입니다."),

    //STORE ERROR
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE4001", "식당이 없져요."),

    //JOIN ERROR
    NAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "JOIN4001", "너의 이름을 정해주겠니.."),
    SEX_NOT_EXIST(HttpStatus.BAD_REQUEST,"JOIN4002", "불분명한 너의 성정체성"),
    BIRTH_YEAR_NOT_EXIST(HttpStatus.BAD_REQUEST,"JOIN4003", "몇 년도에 태어났어.."),
    BIRTH_MONTH_NOT_EXIST(HttpStatus.BAD_REQUEST,"JOIN4004", "몇 월에 태어났을까.."),
    BIRTH_DAY_NOT_EXIST(HttpStatus.BAD_REQUEST,"JOIN4005", "며칠에 태어났는지도 궁금해.."),
    ADDRESS_LENGTH_NOT_SATISFIED(HttpStatus.BAD_REQUEST,"JOIN4006", "기본 주소: 최소 5자 최대 12자 샛끼야"),
    SPEC_ADDRESS_LENGTH_NOT_SATISFIED(HttpStatus.BAD_REQUEST,"JOIN4007", "상세 주소: 최소 5자 최대 12자 샛끼야"),


    //FOOT CATEGORY ERROR
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD_CATEGORY4001", "그런 음식 카테고리는 없다네"),

    //ARTICLE ERROR
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글이 없습니다."),

    //temp
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "For Test.");



    private final HttpStatus httpStatus;
    private final String code;
    private final String message;


    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
