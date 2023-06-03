package airbnb.back.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"isSuccess", "responseCode", "responseMessage", "result"})
/*
    @JsonPropertyOrder : 직렬화시 속성의 순서를 지정
    직렬화란? 자바 시스템 내에서 사용하는 객체 또는 데이터를 자바시스템 외에서도 사용할 수 있도록 Byte 형태로 데이터를 변환하는 기술이다 - 파일로 만든다고 보면 됨
 */
public class BaseResponse<T> {

    private final Boolean isSuccess;
    private final int responseCode;
    private final String responseMessage;

    @JsonInclude(JsonInclude.Include.NON_EMPTY) // result 속성이 비어 있는 경우 JSON에 포함되지 않도록 지정
    private final T result;

    //Request Success, Request 성공시 무조건 result 존재
    public BaseResponse(T result) {
        this.isSuccess = BaseResponseStatus.SUCCESS.isSuccess();
        this.responseMessage = BaseResponseStatus.SUCCESS.getResponseMessage();
        this.responseCode = BaseResponseStatus.SUCCESS.getResponseCode();
        this.result = result;
    }

    //Common Exception, 보내는 데이터 없이 메시지만 출력하면 되는 에러 처리

    public BaseResponse(BaseResponseStatus status) {
        this.isSuccess = status.isSuccess(); //result는 항상 fail
        this.responseMessage = status.getResponseMessage();
        this.responseCode = status.getResponseCode();
        this.result = null;
    }

    //Common Exception, 보내는 데이터 포함하는 메시지 출력하면 되는 에러 처리
    public BaseResponse(BaseResponseStatus status, T result) {
        this.isSuccess = status.isSuccess();
        this.responseMessage = status.getResponseMessage();
        this.responseCode = status.getResponseCode();
        this.result = result;
    }
}
