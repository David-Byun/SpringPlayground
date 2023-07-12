package airbnb.back.util.exception;

import airbnb.back.util.BaseResponseStatus;
import lombok.Getter;

@Getter
public class AwsException extends RuntimeException{
    private final BaseResponseStatus status;

    public AwsException(BaseResponseStatus status) {
        this.status = status;
    }
}
