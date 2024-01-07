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
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "For Test."),

    // authentication 관련 (5000번대)
    JWT_INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH2001", "유효하지 않는 JWT 서명입니다."),
    JWT_EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH2002", "만료된 JWT 토큰입니다."),
    JWT_UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH2003", "지원하지 않는 JWT 토큰입니다."),
    JWT_WRONG_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH2004", "잘못된 JWT 토큰 입니다."),
    JWT_ABSENCE_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH2005", "JWT 토큰이 존재하지 않습니다."),
    AUTH_DUPLICATED_EMAIL(HttpStatus.BAD_REQUEST, "AUTH2006", "중복된 email입니다."),
    AUTH_INVALID_AUTH_CODE(HttpStatus.BAD_REQUEST, "AUTH2007", "유효하지 않는 인증코드입니다."),
    AUTH_EXPIRED_AUTH_CODE(HttpStatus.BAD_REQUEST, "AUTH2008","인증코드가 만료되었습니다."),
    AUTH_VERIFIED_AUTH_CODE(HttpStatus.BAD_REQUEST, "AUTH2009","이미 이메일이 인증되었습니다."),
    AUTH_UNVERIFIED_EMAIL(HttpStatus.BAD_REQUEST, "AUTH2010","인증되지 않은 이메일입니다."),
    AUTH_INVALID_LOGIN_INFO(HttpStatus.NOT_FOUND, "AUTH2011","이메일 또는 비밀번호가 잘못되었습니다."),
    AUTH_EXPIRED_KAKAO_ACCESS_TOKEN(HttpStatus.BAD_REQUEST, "AUTH2012","카카오 엑세스 토큰이 만료되었습니다."),
    AUTH_EXPIRED_OAUTH_TOKEN(HttpStatus.BAD_REQUEST, "AUTH2013","카카오로부터 정보를 받아올 수 없습니다. 다시 로그인해주세요."),
    AUTH_KAKAO_SERVER_ERROR(HttpStatus.BAD_GATEWAY, "AUTH2014","카카오 서버가 일시적 내부 장애상태 입니다"),
    AUTH_DEPRECATED_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "AUTH2015","만료된 Refresh 토큰입니다."),
    AUTH_DEPRECATED_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH2016","더 이상 사용되지 않는 Access 토큰입니다"),
    AUTH_MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "AUTH2017","해당하는 정보의 사용자를 찾을 수 없습니다."),
    AUTH_NOT_ALLOW_FOR_KAKAO_MEMBER(HttpStatus.BAD_REQUEST, "AUTH2018","카카오로 회원가입하신 사용자는 사용할 수 없는 기능입니다."),
    AUTH_WRONG_PASSWORD(HttpStatus.BAD_REQUEST, "AUTH2019","비밀번호가 일치하지 않습니다."),
    AUTH_NOT_ALLOWED_ACCESS(HttpStatus.BAD_REQUEST, "AUTH2020","잘못된 접근입니다."),
    JWT_INVALID_REFRESHTOKEN(HttpStatus.BAD_REQUEST,"AUTH2021","잘못된 RTK입니다."),

    // 이유 불명 (7000번대)
    UNKNOWN_ERROR(HttpStatus.BAD_GATEWAY, "TEMP7001", "알 수 없는 오류가 발생했습니다");

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
