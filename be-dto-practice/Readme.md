### DTO 실습

* DTO : 요청 데이터를 하나의 객체로 전달 받는 역할을 함

getter 는 필수로 들어가야 함

@RequestBody는 요청으로 온 JSON 형식의 RequestBody를 Java로 만들어줌
@ResponseBody는 반대로 java형식의 DTO를 JSON형식으로 응답 형태로 보냄
   
근데 ResponseEntity가 리턴 값이면 내부에 HttpMessageConverter가 동작해서 알아서 바꿔줌


custom Annotation 생성 방법

1. @interface 생성
```
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
```
2. 구현 클래스 생성
```
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OneBlankValidator implements ConstraintValidator<OneBlank, String> {
    @Override
    public void initialize(OneBlank constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches("\\s?[a-zA-Z](\\s?[a-zA-Z])*\\s?$");
    }
}
```
