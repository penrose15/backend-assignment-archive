package a.b.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse {
    private List<FieldError> fieldErrors;
    private List<ConstraintViolationError> violationErrors;

    //클래스 생성자를 막아둠 --> 대신 of메서드로 ErrorResponse 객체 생성
    private ErrorResponse(final List<FieldError> fieldErrors,final List<ConstraintViolationError> violationErrors) {
        this.fieldErrors = fieldErrors;
        this.violationErrors = violationErrors;
    }
    public static ErrorResponse of(BindingResult bindingResult) {
        return new ErrorResponse(FieldError.of(bindingResult),null);
    }//MethodArgumentNotValidException 에 대한 ErrorResponse 객체를 생성
    //이때 BindingResult객체가 필요하므로 메서드를 호출하는 쪽에서 BindingResult객체를 넘기면 됨
    //파라미터 값을 넘기는 것을 아래에 있는 FieldError에게 위임
    public static ErrorResponse of(Set<ConstraintViolation<?>> violations) {
        return new ErrorResponse(null,ConstraintViolationError.of(violations));
    }//ConstraintViolationException 에 대한 ErrorResponse 객체를 생성

    @Getter //(1)MethodArgumentNotValidException에 대한 Error Response
    public static class FieldError {
        //사용자에게 ResponseBody의 모든 정보를 넘겨줄 필요가 없기 때문에
        //아래 세개의 멤버변수만 설정정
       private String field;
        private Object rejectedValue;
        private String reason;

        private FieldError(String field, Object rejectedValue, String reason) {
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        public static List<FieldError> of(BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors =
                    bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue()==null?"" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }
    //(2)URI 변수값 검증에 대한 ErrorResponse
    @Getter
    public static class ConstraintViolationError {
        private String propertyPath;
        private Object rejectedValue;
        private String reason;

        private ConstraintViolationError(String propertyPath, Object rejectedValue, String reason) {
            this.propertyPath = propertyPath;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }
        public static List<ConstraintViolationError> of(Set<ConstraintViolation<?>> constraintViolations) {
            return constraintViolations.stream()
                    .map(constraintViolation -> new ConstraintViolationError(
                            constraintViolation.getPropertyPath().toString(),
                            constraintViolation.getInvalidValue().toString(),
                            constraintViolation.getMessage()))
                    .collect(Collectors.toList());
        }
    }
}
