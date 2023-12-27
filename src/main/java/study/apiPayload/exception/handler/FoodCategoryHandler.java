package study.apiPayload.exception.handler;

import study.apiPayload.code.BaseErrorCode;
import study.apiPayload.code.status.ErrorStatus;
import study.apiPayload.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {

    public FoodCategoryHandler(BaseErrorCode code) {
        super(code);
    }

    public static FoodCategoryHandler foodCategoryNotFound(String category) {
        return new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND
        );
    }
}
