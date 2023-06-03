package airbnb.back.util.exception;

import airbnb.back.util.BaseResponseStatus;
import lombok.Getter;

@Getter
public class RoomException extends RuntimeException {
    private final BaseResponseStatus status;

    public RoomException(BaseResponseStatus status) {
        this.status = status;
    }
}
