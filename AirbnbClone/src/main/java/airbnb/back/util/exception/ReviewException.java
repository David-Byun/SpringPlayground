package airbnb.back.util.exception;

import airbnb.back.util.BaseResponseStatus;
import lombok.Getter;

@Getter
public class ReviewException extends RuntimeException{
    private final BaseResponseStatus status;

    public ReviewException(BaseResponseStatus status) {
        this.status = status;
    }
}
