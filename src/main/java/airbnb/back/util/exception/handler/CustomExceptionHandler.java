package airbnb.back.util.exception.handler;

import airbnb.back.util.BaseResponse;
import airbnb.back.util.BaseResponseStatus;
import airbnb.back.util.exception.ReviewException;
import airbnb.back.util.exception.RoomException;
import airbnb.back.util.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static airbnb.back.util.BaseResponseStatus.*;
import static airbnb.back.util.BaseResponseStatus.INVALID_REQUEST;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    //파라미터가 없는 경우
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public BaseResponse<Object> handleRequestParameter() {
        return new BaseResponse<>(EMPTY_REQUEST_PARAMETER);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public BaseResponse<Object> handleValidationException(MethodArgumentNotValidException e) {

        Map<String, String> errors = new HashMap<>();
        log.info("errors = {}", e.getBindingResult().getFieldError().getDefaultMessage());

        // forEach로 모든 에러 메세지를 던집니다.
        e.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        return new BaseResponse<>(INVALID_REQUEST, errors);

    }

    @ExceptionHandler({UserException.class})
    public BaseResponse<Object> handleUserException(UserException e) {
        return new BaseResponse<>(e.getStatus());
    }

    @ExceptionHandler({RoomException.class})
    public BaseResponse<Object> handleRoomException(RoomException e) {
        return new BaseResponse<>(e.getStatus());
    }

    @ExceptionHandler({ReviewException.class})
    public BaseResponse<Object> handleReviewException(ReviewException e) {
        return new BaseResponse<>(e.getStatus());
    }

    @ExceptionHandler({AwsException.class})
    public BaseResponse<Object> handleUserException(ReviewException e) {
        return new BaseResponse<>(e.getStatus());
    }
}
