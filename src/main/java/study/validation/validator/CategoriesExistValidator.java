package study.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.apiPayload.code.status.ErrorStatus;
import study.validation.annotation.ExistCategories;
import study.service.FoodCategoryService.FoodCategoryService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
@Component
@RequiredArgsConstructor
public class CategoriesExistValidator implements ConstraintValidator<ExistCategories, List<Long>> {
//isValid 메소드의 반환 값을 확인하여 검증이 되었는지 실패했는 지를 알려줌
//isValid의 리턴 값이 false면 ConstraintViolationException 을 발생시킴
    private final FoodCategoryService foodCategoryService;

    //서비스 가져오기. 서비스 거쳐서 Impl 안에 레포지토리 넣기

    @Override
    public void initialize(ExistCategories constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
        boolean isValid = values.stream()
                .allMatch(foodCategoryService::existsById);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.FOOD_CATEGORY_NOT_FOUND.getMessage()).addConstraintViolation();
        }

        return isValid;
    }
}