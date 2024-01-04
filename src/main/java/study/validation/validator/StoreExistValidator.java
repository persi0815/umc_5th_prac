package study.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.apiPayload.code.status.ErrorStatus;
import study.service.StoreService.StoreQueryService;
import study.validation.annotation.ExistCategories;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StoreExistValidator implements ConstraintValidator<ExistCategories, List<Long>> {
    private final StoreQueryService storeQueryService;

    @Override
    public void initialize(ExistCategories constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
        boolean isValid = values.stream()
                .allMatch(storeQueryService::existsById);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.getMessage()).addConstraintViolation();
        }

        return isValid;
    }
}
