package study.validation.annotation;

import study.validation.validator.CategoriesExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/*
@Documented - 사용자 정의 어노테이션을 만들 때 붙입니다.
@Constraint - java에서 제공하는 사용자가 validation을 커스텀 어노테이션을 통해 할 수 있도록 제공하는 어노테이션
@Target - 어노테이션의 적용 범위를 지정합니다.
@Retention - 어노테이션의 생명 주기를 지정합니다. 위의 코드는 RUNTIME이기에 실행 하는 동안에만 유효하게 됩니다.
 */
@Documented
@Constraint(validatedBy = CategoriesExistValidator.class)
//CategoriesExistValidator라는 클래스를 통해 @ExistCategories가 붙은 대상을 검
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistCategories {

    String message() default "해당하는 카테고리가 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}