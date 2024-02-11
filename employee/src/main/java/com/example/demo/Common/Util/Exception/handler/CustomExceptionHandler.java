package com.example.demo.Common.Util.Exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler({ReservationException.class})
    public BaseResponse<Object> handleReservationException(ReservationException e) {
        return new BaseResponse<>(e.getStatus());
    }
}
