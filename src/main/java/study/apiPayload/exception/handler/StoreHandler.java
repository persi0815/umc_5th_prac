package study.apiPayload.exception.handler;

import study.apiPayload.code.BaseErrorCode;
import study.apiPayload.exception.GeneralException;

public class StoreHandler extends GeneralException {

    public StoreHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}