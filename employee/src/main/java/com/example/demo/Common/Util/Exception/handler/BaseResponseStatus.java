package com.example.demo.Common.Util.Exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseResponseStatus {

    SUCCESS(true, 1000, "요청에 성공하였습니다.");

    private final boolean isSuccess;
    private final int responseCode;
    private final String responseMessage;
}
