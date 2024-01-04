package study.apiPayload.exception.handler;

import study.apiPayload.code.BaseErrorCode;
import study.apiPayload.code.status.ErrorStatus;
import study.apiPayload.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {

    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}

