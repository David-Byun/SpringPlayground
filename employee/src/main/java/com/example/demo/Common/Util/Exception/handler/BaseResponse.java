package com.example.demo.Common.Util.Exception.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import static com.example.demo.Common.Util.Exception.handler.BaseResponseStatus.SUCCESS;

@Getter
@JsonPropertyOrder({"isSuccess", "responseCode", "responseMessage", "result"})
public class BaseResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    private final int responseCode;
    private final String responseMessage;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final T result;

    // Request Success, Request 성공시 무조건 result 존재
    public BaseResponse(T result) {
        this.isSuccess = SUCCESS.isSuccess();
        this.responseCode = SUCCESS.getResponseCode();
        this.responseMessage = SUCCESS.getResponseMessage();
        this.result = result;
    }

    public BaseResponse(BaseResponseStatus status) {
        this.isSuccess = status.isSuccess();
        this.responseCode = status.getResponseCode();
        this.responseMessage = status.getResponseMessage();
        this.result = null;
    }
}
