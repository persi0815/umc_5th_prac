package study.apiPayload.code;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ErrorReasonDTO {
    private String message;
    private String code;
    private Boolean isSuccess;
    private HttpStatus httpStatus;

    public boolean getIsSuccess(){return isSuccess;}
}
