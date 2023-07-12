package airbnb.back.util.exception;

import airbnb.back.util.BaseResponseStatus;
import lombok.Getter;

@Getter
public class JwtInvalidException extends RuntimeException{

    private final BaseResponseStatus status;

    public JwtInvalidException(BaseResponseStatus status) {
        this.status = status;
    }
}
