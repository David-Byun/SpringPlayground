package airbnb.back.util.exception;

import airbnb.back.util.BaseResponseStatus;
import lombok.Getter;

@Getter
public class UserException extends RuntimeException {

    private final BaseResponseStatus status;

    public UserException(BaseResponseStatus status) {
        this.status = status;
    }


}
