package study.apiPayload.exception.handler;

import study.apiPayload.code.BaseErrorCode;
import study.apiPayload.code.status.ErrorStatus;
import study.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode code) {
        super(code);
    }

    public static FoodCategoryHandler foodCategoryNotFound(String category) {
        return new FoodCategoryHandler(ErrorStatus.MEMBER_NOT_FOUND);
    }
}
//MEMBER_NOT_FOUND
