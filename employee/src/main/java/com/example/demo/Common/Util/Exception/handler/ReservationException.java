package com.example.demo.Common.Util.Exception.handler;

import lombok.Getter;

@Getter
public class ReservationException extends RuntimeException {

    private final BaseResponseStatus status;

    public ReservationException(BaseResponseStatus status) {
        this.status = status;
    }
}
