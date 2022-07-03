package exalted.posset.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum ExceptionCode {
    MEMBER_EXIST(400, "member already exist"),
    MEMBER_NOT_FOUND(404,"member not exist"),
    COFFEECODE_EXIST(400, "coffeecode already exist"),
    COFFEECODE_NOT_FOUND(404, "coffeeCode notExist"),
    COFFEEID_NOT_FOUND(404,"coffeeId not exist"),

    ORDER_NOT_EXIST(404, "order not exist"),
    ORDER_EXIST(400,"order already Exist"),
    ORDER_CANNOT_CANCEL(400,"order cannot cancel");

    private int status;
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
