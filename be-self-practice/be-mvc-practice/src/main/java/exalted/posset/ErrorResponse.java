package exalted.posset;

import exalted.posset.exception.BusinessLogicException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;

    private ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
    public static ErrorResponse of(BusinessLogicException e) {
        int status = e.getExceptionCode().getStatus();
        String message = e.getExceptionCode().getMessage();
        return new ErrorResponse(status, message);
    }
}
