package airbnb.back.util.exception;

import airbnb.back.util.BaseResponseStatus;
import lombok.Getter;

// ReservationException
@Getter
public class ReservationException extends RuntimeException{

    private final BaseResponseStatus status;

    public ReservationException(BaseResponseStatus status) {
        this.status = status;
    }
}
