package com.codestates.coffee;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OneBlankValidator.class)
//멤버변수가 추가시 동작할 클라스
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OneBlank {
    String message() default "영어이고 빈칸 한칸만 허용됩니다.";
    //유효성 검증 실패시 표시되는 메시지
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
