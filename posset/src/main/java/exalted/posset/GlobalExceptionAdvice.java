package exalted.posset;

import exalted.posset.ErrorResponse;
import exalted.posset.exception.BusinessLogicException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleExceptionCode(BusinessLogicException b) {
        final ErrorResponse response = ErrorResponse.of(b);
        return response;
    }


}
