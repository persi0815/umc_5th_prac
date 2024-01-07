package study.apiPayload.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import study.apiPayload.code.status.ErrorStatus;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException{
    public ErrorStatus errorStatus;
}
