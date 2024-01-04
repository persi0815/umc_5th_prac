package study.apiPayload.exception.handler;

import study.apiPayload.code.BaseErrorCode;
import study.apiPayload.exception.GeneralException;

public class MemberJoinHandler extends GeneralException {

    public MemberJoinHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}