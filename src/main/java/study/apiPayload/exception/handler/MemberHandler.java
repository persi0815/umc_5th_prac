package study.apiPayload.exception.handler;

import study.apiPayload.code.BaseErrorCode;
import study.apiPayload.code.status.ErrorStatus;
import study.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {

    public MemberHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
