package com.mang.atdd.membership;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum MembershipErrorResult {

    DUPLICATED_MEMBERSHIP_REGISTER(HttpStatus.BAD_REQUEST, "Duplicated Membership Register Request"),
    UNKNOWN_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown Exception"),
    MEMBERSHIP_NOT_FOUND(HttpStatus.NOT_FOUND, "Membership Not Found"),
    NOT_MEMBERSHIP_OWNER(HttpStatus.NOT_FOUND, "NOT a membership owner"),
    ;
    private final HttpStatus httpStatus;
    private final String message;

    MembershipErrorResult(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }


}
